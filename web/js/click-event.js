
//SUBMIT FORM MODAL-BOX
document.getElementById("modal_form").addEventListener("submit", function (event) {
    event.preventDefault(); // Ngăn chặn reload trang

    let productId = document.getElementById("modal_id").value;
    let quantity = document.getElementById("quantity").value;

    addToCart(productId, quantity);
});

function addToCart(productId, quantity) {

    $.ajax({
        url: "buy",
        type: "POST",
        data: {id: productId, num: quantity},
        success: function () {
            updateCartSize();
            let modalElement = bootstrap.Modal.getInstance(document.getElementById("modal_box"));
            modalElement.hide();
        },
        error: function (xhr, status, error) {
            console.error("Lỗi khi thêm vào giỏ hàng: " + error);
        }
    });
}

function updateCartSize() {
    $.ajax({
        url: "sizeCart",
        type: "GET",
        success: function (response) {
            $("#cartSize").html('<img src="images/cart.svg">(' + response.cartSize + ')');
        },
        error: function (xhr, status, error) {
            console.error("Lỗi khi lấy số lượng giỏ hàng: " + error);
        }
    });
}

$(document).ready(function () {
    updateCartSize();
});

function openModal(productId, image, name, price, description) {

    let decodedDescription = description
            .replace(/&amp;/g, "&")
            .replace(/&lt;/g, "<")
            .replace(/&gt;/g, ">")
            .replace(/&#39;/g, "'")
            .replace(/&quot;/g, '"');

    document.getElementById("modal_image1").src = image;
    document.getElementById("modal_image2").src = image;
    document.getElementById("modal_thumb1").src = image;
    document.getElementById("modal_thumb2").src = image;
    document.getElementById("modal_name").innerText = name;
    document.getElementById("modal_salePrice").innerText = price;
    document.getElementById("modal_description").innerText = decodedDescription;
    document.getElementById("modal_companyName").innerText = "Thang-company";
    document.getElementById("quantity").value = 1;
    document.getElementById("modal_id").value = productId;

    let modalElement = new bootstrap.Modal(document.getElementById("modal_box"));
    modalElement.show();
}


