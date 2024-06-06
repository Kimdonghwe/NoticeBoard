document.addEventListener('DOMContentLoaded', function () {
    const email = document.getElementById('email');
    const pw = document.getElementById('pw');
    const nickname = document.getElementById('nickname');
    const tel = document.getElementById('tel');

    const emailError = document.getElementById('email_error');
    const pwError = document.getElementById('pw_error');
    const nicknameError = document.getElementById('nickname_error');
    const telError = document.getElementById('tel_error');

    email.addEventListener('focusout', function () {
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,30}$/;
        if (email.value.length > 30 || !emailRegex.test(email.value)) {
            emailError.style.display = 'inline';
        } else {
            emailError.style.display = 'none';
        }
    });

    pw.addEventListener('focusout', function () {
        const pwRegex = /^[a-zA-Z0-9!@#$%^&*()_+={}:;"'<>,.?/~`-]{1,20}$/;
        if (pw.value.length > 20 || pw.value.length < 8 || !pwRegex.test(pw.value)) {
            pwError.style.display = 'inline';
        } else {
            pwError.style.display = 'none';
        }
    });

    nickname.addEventListener('focusout', function () {
        const nicknameRegex = /^[a-zA-Z가-힣0-9!@#$%^&*()_+={}:;"'<>,.?/~`-]{1,10}$/;
        if (nickname.value.length > 10 || !nicknameRegex.test(nickname.value)) {
            nicknameError.style.display = 'inline';
        } else {
            nicknameError.style.display = 'none';
        }
    });

    tel.addEventListener('focusout', function () {
        const telRegex = /^010\d{8}$/;
        if (!telRegex.test(tel.value)) {
            telError.style.display = 'inline';
        } else {
            telError.style.display = 'none';
        }
    });
});
