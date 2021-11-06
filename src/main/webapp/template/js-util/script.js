// import Swal from "https://cdn.jsdelivr.net/npm/sweetalert2@11"
// import Swal from 'https://cdn.jsdelivr.net/npm/sweetalert2@11.1.7/dist/sweetalert2.all.min.js'
import Swal from 'https://cdn.jsdelivr.net/npm/sweetalert2@8/src/sweetalert2.js'

function getSiteRoot () {
    let _location = window.location.toString();
    let applicationIndex = _location.indexOf("/", _location.indexOf("://")+window.location.hostname.length);
    let applicationName = _location.substring(applicationIndex, );
    return applicationName;
}

function showNotification(title, message, type, url) {
    Swal.fire({
        title: title,
        text: message,
        icon: type,
        confirmButtonText: "Đóng",
        closeOnConfirm: true,
        allowEscapeKey: false,
        allowOutsideClick: false
    }).then(function (result) {
        if (result.isConfirmed) {
            window.location.href = url;
        }
    });
}