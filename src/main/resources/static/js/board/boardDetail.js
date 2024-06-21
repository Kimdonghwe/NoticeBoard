document.addEventListener("DOMContentLoaded", function () {

    // 날짜 부분만 추출
    const timeElement = document.querySelector('.udate');
    const date = new Date(timeElement.textContent);
    const formattedDate = date.toISOString().split('T')[0]; 
    timeElement.textContent = formattedDate;

    const noticeBoardId = document.getElementById("title").getAttribute("data-noticeboard-id");
    isLike(); // 회원이 해당 게시글에 좋아요를 선택했는지 판별
    calculateHit(); // 페이지 불러올때마다 db에서 해당게시글 조회수 1올리고 js딴에서도 수치 1올림

    if(document.getElementById("edit-Btn")){

        document.getElementById("edit-Btn").addEventListener("click", evt =>{
            
            editPage(noticeBoardId);
        });
    }

    if(document.getElementById("delete-Btn")){
    // 추후 삭제버튼 누르면 modal 창으로 삭제확인 하는 로직추가 필요
    document.getElementById("delete-Btn").addEventListener("click", evt =>{

        showDeleteConfirmModal(noticeBoardId);
    });
    }


    document.querySelector(".like-button-wrap").addEventListener("click", evt => { 
        toggleLike()
    });

    // 초기 상태 설정
function isLike(){
    fetch(`/api/prefer/isliked?noticeboardId=${noticeBoardId}`)
    .then(response => response.json())
    .then(isLiked => {
        if (isLiked) {
            likeButton.setAttribute('data-liked', 'true');
            likeButton.classList.add('liked');
        } else {
            likeButton.setAttribute('data-liked', 'false');
        }
    })
    .catch(error => console.error('Error:', error));
}

     // 좋아요 기능
     function toggleLike() {
        const isLiked = likeButton.getAttribute('data-liked') === 'true';

        fetch(`/api/prefer/like?noticeboardId=${noticeBoardId}`, {
            method: 'POST'
        })
        .then(response => response.text())
        .then(result => {
            if (result === 'Success') {
                if (isLiked) {
                    likeButton.setAttribute('data-liked', 'false');
                    likeButton.classList.remove('liked');
                    likeCount.textContent = parseInt(likeCount.textContent) - 1;
                } else {
                    likeButton.setAttribute('data-liked', 'true');
                    likeButton.classList.add('liked');
                    likeCount.textContent = parseInt(likeCount.textContent) + 1;
                }
            } else {
                console.error('좋아요 처리에 실패했습니다.');
            }
        })
        .catch(error => console.error('Error:', error));
    }

    // 조회수 기능
    function calculateHit() { 

        fetch(`/api/board/hit?noticeboardId=${noticeBoardId}`, {
            method: 'GET'
        })
        .then(response => response.text())
        .then(result => {
            if (result === 'Success') {
                document.querySelector('.hit').textContent = parseInt(document.querySelector('.hit').textContent) + 1
            } else {
                console.error('조회수 처리에 실패했습니다.');
            }
        })
        .catch(error => console.error('Error:', error));


    }


});
function editPage(noticeboardId) {
    location.href = `/board/edit/${noticeboardId}`;
}

function deltePage(noticeboardId) {
    location.href = `/board/delete/${noticeboardId}`;
}

function showDeleteConfirmModal(noticeBoardId) {
    // 모달 창을 보여줌
    $('#deleteConfirmModal').modal('show');

    // 모달의 확인 버튼 클릭 시 삭제 기능 수행
    document.getElementById('confirmDeleteBtn').onclick = function() {
        deltePage(noticeBoardId);
        $('#deleteConfirmModal').modal('hide');
    };
}




   