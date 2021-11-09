
function validateBookInfo () {
    let completed = true;
    let name = $('#inputName').val();
    let description = $('#description').val();
    let cover = $('#inputCover').val();
    let pageNumber = $('#inputPageNumber').val();
    let publishedYear = $('#inputPublishedYear').val();
    let price = $('#inputPrice').val();
    let quantity = $('#inputQuantity').val();

    if (name.trim() === '') {
        completed = false;
        $('#inputNameStatus').text("Nhập tên sách");
    }
    if (description.trim() === '') {
        completed = false;
        $('descriptionStatus').text("Nhập Mô tả");
    }
    if (pageNumber.trim() === '') {
        completed = false;
        $('#inputPageNumberStatus').text("Nhập số trang");
    }
    if (cover.trim() === '') {
        completed = false;
        $('#inputCoverStatus').text("Nhập hình thức bìa của quyển sách");
    }
    if (publishedYear.trim() === '') {
        completed = false;
        $('#inputPublishedYearStatus').text("Nhập năm xuất bản");
    }
    if (price.trim() === '') {
        completed = false;
        $('#inputPriceStatus').text("Nhập giá bán của quyển sách ");
    }
    if (quantity.trim() === '') {
        completed = false;
        $('#inputQuantityStatus').text("Nhập số lượng nhập");
    }
    return completed;
}