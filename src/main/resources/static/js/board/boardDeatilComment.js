document.addEventListener("DOMContentLoaded", function () {

    displayComments();
    
    document.addEventListener('click', function(event) {
        var textarea = document.getElementById('comment-textarea');
        var button = document.getElementById('comment-submit');
    
        if (textarea.contains(event.target)) {
            button.style.display = 'block';
        } else {
            button.style.display = 'none';
        }
    });


    document.getElementById('comment-submit').addEventListener('click', function() {

        const commentCnt = document.querySelectorAll(".commentCount");

        var noticeboardId = document.getElementById("title").getAttribute("data-noticeboard-id");
        var content = document.getElementById('comment-textarea').value.trim();
        document.getElementById('comment-textarea').value = '';


        var data = {
            noticeboardId: noticeboardId,
            content: content,
        };

        fetch('/api/comment/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            if (data.header.rtcd === '00') {
                commentCnt[0].textContent = parseInt(commentCnt[0].textContent) + 1;
                commentCnt[1].textContent = parseInt(commentCnt[1].textContent) + 1;
                displayComments();
            } else {
                console.error('Error: ', data.header.rfmsg);
                // 오류 메시지를 사용자에게 표시하는 로직 추가 가능
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    });


    function displayComments() {
        fetch('/api/comment/all', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.header.rtcd === '00') {
                var comments = data.body;
                var template = document.getElementById('commentTemplate');
                var commentList = document.querySelector('.rbbs-List-wrapper');
                
                commentList.innerHTML = ''; // 기존 댓글들 초기화
                
                comments.forEach(comment => {
                    var clone = document.importNode(template.content, true);
                    clone.querySelector('.comment-nickname').textContent = comment.nickname;
                    clone.querySelector('.comment-udate').textContent = comment.udate; // 서버에서 받은 생성 날짜
                    clone.querySelector('.comment-wrap p').textContent = comment.content;

                    var headInfo = clone.querySelector('.head-info');
                    headInfo.dataset.commentId = comment.commentId; // data-comment-id 속성 설정

                    var commentActions = clone.querySelector('#comment-actions');
                    if (userNickname != comment.nickname) {
                        commentActions.style.display = 'none';
                    }

                    clone.querySelector('#edit-Btn').addEventListener('click', function() {
                        var parentElement = event.target.closest('.item');
                        showEditCommentInput(parentElement, comment);
                    });

                    clone.querySelector('#delete-Btn').addEventListener('click', function() {
                        deleteComment(comment.commentId);
                    });

                    commentList.appendChild(clone);
                });
            } else {
                console.error('Error: ', data.header.rfmsg);
                // 오류 메시지를 사용자에게 표시하는 로직 추가 가능
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }

    function showEditCommentInput(parentElement, comment) {
        // 이미 열려 있는 수정 입력창이 있으면 닫기
        var existingEditComment = document.querySelector('.edit-comment-wrap');
        if (existingEditComment && existingEditComment.parentElement) {
            existingEditComment.parentElement.removeChild(existingEditComment);
        }
    
        var template = document.getElementById('editCommentTemplate');
        var clone = document.importNode(template.content, true);
        
        var textarea = clone.querySelector('.edit-comment-textarea');
        textarea.value = comment.content;
    
        var submitButton = clone.querySelector('.edit-comment-submit');

        console.log(comment.commentId, comment.noticeboardId , textarea.value.trim());
        submitButton.addEventListener('click', function() {
            var updatedContent = textarea.value.trim();
            if (updatedContent) {
                var data = {
                    commentId: comment.commentId,
                    noticeboardId: document.getElementById("title").getAttribute("data-noticeboard-id"), // 원래 댓글의 공지사항 ID
                    content: updatedContent
                };
    
                fetch('/api/comment/update', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                .then(response => response.json())
                .then(data => {
                    if (data.header.rtcd === '00') {
                        displayComments();
                    } else {
                        console.error('Error: ', data.header.rfmsg);
                    }
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
            }
        });
    
       // parentElement의 다음 형제로 수정 입력창 추가
    parentElement.parentNode.insertBefore(clone, parentElement.nextSibling);
    
        // 다른 부분을 클릭하면 수정 입력창이 사라지게 하는 로직
        document.addEventListener('click', function(event) {
            if (!parentElement.contains(event.target) && !clone.contains(event.target)) {
                if (clone.parentElement) {
                    clone.parentElement.removeChild(clone);
                }
            }
        }, { once: true });
    }
    
    function deleteComment(commentId) {
        const commentCnt = document.querySelectorAll(".commentCount");
        fetch(`/api/comment/delete/${commentId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.header.rtcd === '00') {
                commentCnt[0].textContent = parseInt(commentCnt[0].textContent) - 1;
                commentCnt[1].textContent = parseInt(commentCnt[1].textContent) - 1;
                displayComments();
            } else {
                console.error('Error: ', data.header.rfmsg);
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
});

