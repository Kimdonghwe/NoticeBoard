document.addEventListener("DOMContentLoaded", function () { 

    document.getElementById('dropdownButton').addEventListener('click', function() {
        var menu = document.getElementById('dropdownMenu');
        if (menu.style.display === 'none' || menu.style.display === '') {
            menu.style.display = 'block';
            var rect = this.getBoundingClientRect();
            menu.style.top = rect.bottom + 'px';
            menu.style.left = rect.left + 'px';
        } else {
            menu.style.display = 'none';
        }
    });

    // 메뉴 외부를 클릭하면 메뉴를 닫기
    window.addEventListener('click', function(event) {
        var menu = document.getElementById('dropdownMenu');
        if (!event.target.matches('#dropdownButton')) {
            if (menu.style.display === 'block') {
                menu.style.display = 'none';
            }
        }
    });
});