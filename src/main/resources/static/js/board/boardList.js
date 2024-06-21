document.addEventListener("DOMContentLoaded", function () {

	 // 날짜 부분만 추출
	 const timeElements = document.querySelectorAll('.udate');
	 timeElements.forEach(timeElement => {
		 const date = new Date(timeElement.textContent);
		 const formattedDate = date.toISOString().split('T')[0] + ' ' + date.toTimeString().split(' ')[0];
		 timeElement.textContent = formattedDate;
	 });

	const boardItems = document.querySelectorAll(".board-item");
	const buttons = document.querySelectorAll('.category-btn');
	const categoryValue = document.querySelector('input[name="codeId"]').value;

	buttons.forEach(button => {
		if (button.getAttribute('data-code-id') === categoryValue) {
			button.classList.add('active');
		}
	});

	// 각 board-item에 클릭 이벤트 리스너 추가
	boardItems.forEach(function (item) {
		item.addEventListener("click", function () {
			// data-management-id 값을 가져오기
			const managementId = item.getAttribute("data-management-id");
			const noticeboardId = item.querySelector(".noticeBoardNum").getAttribute("data-noticeboard-id");

			location.href = `/board/detail/${noticeboardId}`;

			// 다른 동작 예시
			// alert('Clicked item with management ID: ' + managementId);
			// window.location.href = '/noticeBoard/' + managementId; // 다른 페이지로 이동
		});
	});

	
});
