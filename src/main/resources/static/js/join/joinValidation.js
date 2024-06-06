document.addEventListener('DOMContentLoaded', function () {
    const joinForm = document.getElementById('joinForm');

    const email = document.getElementById('email');
    const pw = document.getElementById('pw');
    const nickname = document.getElementById('nickname');
    const tel = document.getElementById('tel');

    const duplicationBtn = document.getElementById('duplication_btn');

    const emailError = document.getElementById('email_error');
    const pwError = document.getElementById('pw_error');
    const nicknameError = document.getElementById('nickname_error');
    const telError = document.getElementById('tel_error');

    // 이메일 유효성검사
    email.addEventListener('input', function () {
        duplicationBtn.dataset.dupchk = 'nok';
    });

    // 이메일 유효성검사
    email.addEventListener('focusout', function () {
        email.dataset.chk = 'nok';
        if (email.value.trim().length === 0) {
            emailError.textContent = '아이디를 입력해주세요.';
            email.focus();
            return;
        }
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,30}$/;
        if (email.value.length > 30 || !emailRegex.test(email.value)) {
            emailError.style.display = 'inline';
        } else {
            emailError.style.display = 'none';
            email.dataset.chk = 'ok';
        }

        email.value = email.value.trim();
    });

    // 비밀번호 유효성검사
    pw.addEventListener('focusout', function () {
        pw.dataset.chk = 'nok';
        if (pw.value.trim().length === 0) {
            pwError.textContent = '비밀번호를 입력해주세요.';
            pw.focus();
            return;
        }

        const pwRegex = /^[a-zA-Z0-9!@#$%^&*()_+={}:;"'<>,.?/~`-]{1,20}$/;
        if (pw.value.length > 20 || pw.value.length < 8 || !pwRegex.test(pw.value)) {
            pwError.style.display = 'inline';
        } else {
            pwError.style.display = 'none';
            pw.dataset.chk = 'ok';
        }
        pw.value = pw.value.trim();
    });

    // 닉네임 유효성검사
    nickname.addEventListener('focusout', function () {
        nickname.dataset.chk = 'nok';
        if (nickname.value.trim().length === 0) {
            nicknameError.textContent = '닉네임를 입력해주세요.';
            nickname.focus();
            return;
        }

        const nicknameRegex = /^[a-zA-Z가-힣0-9!@#$%^&*()_+={}:;"'<>,.?/~`-]{1,10}$/;
        if (nickname.value.length > 10 || !nicknameRegex.test(nickname.value)) {
            nicknameError.style.display = 'inline';
        } else {
            nicknameError.style.display = 'none';
            nickname.dataset.chk = 'ok';
        }

        nickname.value = nickname.value.trim();
    });

    // 전화번호 유효성검사
    tel.addEventListener('focusout', function () {
        tel.dataset.chk = 'nok';
        const telRegex = /^010\d{8}$/;
        if (!telRegex.test(tel.value)) {
            telError.style.display = 'inline';
        } else {
            telError.style.display = 'none';
            tel.dataset.chk = 'ok';
        }
    });

    // 중복확인 버튼
    duplicationBtn.addEventListener('click', function () {
        email.focus();
        dupchk();
    });

    async function dupchk() {
        const emailIdValue = email.value.trim();

        // 중복 체크 요청 데이터 생성
        const data = { email: emailIdValue };

        const url = `/api/join/dupchk`;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                accept: 'application/json',
            },
            body: JSON.stringify(data),
        };

        try {
            const response = await fetch(url, options);
            const msgBody = await response.json();

            // 중복 체크 결과에 따른 처리
            if (msgBody.header.rtcd === '21') {
                console.log('ID exists');
                emailError.style.display = 'inline';
                emailError.textContent = '이미 사용 중인 아이디입니다.';
                duplicationBtn.dataset.dupchk = 'nok';
            } else {
                console.log('ID does not exist');
                emailError.style.display = 'inline';
                emailError.textContent = '사용 가능한 아이디입니다.';
                duplicationBtn.dataset.dupchk = 'ok';
            }
        } catch (error) {
            console.error('Error:', error);
            alert('서버와의 통신 중 오류가 발생했습니다.');
        }
    }

    // 폼 제출 이벤트 리스너 추가
    joinForm.addEventListener('submit', function (event) {
        if (email.dataset.chk !== 'ok' || pw.dataset.chk !== 'ok' || nickname.dataset.chk !== 'ok' || tel.dataset.chk !== 'ok') {
            event.preventDefault();
            if (email.dataset.chk !== 'ok') {
                email.focus();
            } else if (pw.dataset.chk !== 'ok') {
                pw.focus();
            } else if (nickname.dataset.chk !== 'ok') {
                nickname.focus();
            } else if (tel.dataset.chk !== 'ok') {
                tel.focus();
            }
            alert('입력한 정보를 다시 확인해주세요.');
        }
    });
});
