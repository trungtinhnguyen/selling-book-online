// import Swal from '/template/sweet-alert/dist/sweetalert'
// import ('/template/sweet-alert/dist/sweetalert.css')

function validateLoginForm () {
    let isCompleted = true;
    let inputUsername = $('#inputUsername');
    let inputPassword = $('#inputPassword');

    if (inputUsername.val() === '') {
       $('#inputUsernameStatus').text('Vui lòng nhập tên đăng nhập');
       isCompleted = false;
    } else {
        $('#inputUsernameStatus').text('');
    }
    if (inputPassword.val() === '') {
        $('#inputPasswordStatus').text('Vui lòng nhập mật khẩu');
        isCompleted = false;
    } else {
        $('#inputPasswordStatus').text('');
    }
    if (isCompleted) {
       $('#formLogin').submit();
    }
}

function validateRegisterForm () {
    let isCompleted = true;
    let fullName = $('#inputFullName').val();
    let username = $('#inputUsername').val();
    let email = $('#inputEmail').val();
    let password = $('#inputPassword').val();
    let passwordConfirm = $('#inputPasswordConfirm').val();
    let tell = $('#inputTell').val();
    let address = $('#inputAddress').val();

    if (fullName === '') {
        $('#inputFullNameStatus').text("Vui lòng nhập họ tên");
        isCompleted = false;
    } else {
        $('#inputFullNameStatus').text("");
    }

    if (username === '') {
        $('#inputUsernameStatus').text("Tên đăng nhập không được bỏ trống");
        isCompleted = false;
    } else {
        $('#inputUsernameStatus').text("");
    }

    if (email === '') {
        $('#inputEmailStatus').text("Vui lòng điền email");
        isCompleted = false;
    } else {
        $('#inputEmailStatus').text("");
    }

    if (password === '') {
        $('#inputPasswordStatus').text("Chưa điền mật khẩu");
        isCompleted = false;
    } else {
        $('#inputPasswordStatus').text("");
    }

    if (passwordConfirm !== password) {
        $('#inputPasswordConfirmStatus').text("Mật khẩu không trùng khớp");
        isCompleted = false;
    } else {
        $('#inputPasswordConfirmStatus').text("");
    }

    if (tell === '') {
        $('#inputTellStatus').text("Vui lòng nhập số điện thoại");
        isCompleted = false;
    } else {
        $('#inputTellStatus').text("");
    }

    if (address === '') {
        $('#inputAddressStatus').text("Vui lòng địa chỉ");
        isCompleted = false;
    } else {
        $('#inputAddressStatus').text("");
    }

   return isCompleted;
}
