document.addEventListener('DOMContentLoaded', function () {
    // 회원가입 년도 데이터 입력
    var yearSelect = document.getElementById('birthYear');
    var currentYear = new Date().getFullYear();
    var earliestYear = 1900;

    for (let year = currentYear; year >= earliestYear; year--) {
        var option = document.createElement('option');
        option.value = year;
        option.textContent = year + '년';
        yearSelect.appendChild(option);
    }
    //

    // 비밀번호 확인 pw와 pw_check의 들어간 두 갑이 같은지 비교
    const password = document.getElementById('pw');
    const pwCheck = document.getElementById('pw_check');
    const pwCheckError = document.getElementById('pw_check_error');

    pwCheck.addEventListener('focusout', function () {
        if (password.value !== pwCheck.value) {
            pwCheckError.style.display = 'inline';
        } else {
            pwCheckError.style.display = 'none';
        }
    });

    pwCheck.addEventListener('input', function () {
        if (pwCheckError.style.display === 'inline') {
            if (password.value === pwCheck.value) {
                pwCheckError.style.display = 'none';
            }
        }
    });

    password.addEventListener('input', function () {
        if (pwCheckError.style.display === 'inline') {
            if (password.value === pwCheck.value) {
                pwCheckError.style.display = 'none';
            }
        }
    });
});
