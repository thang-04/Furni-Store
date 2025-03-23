
//SUBMIT FORM MODAL-BOX
document.getElementById("modal_form").addEventListener("submit", function (event) {
    event.preventDefault(); 
    
    let productId = document.getElementById("modal_id").value;
    let quantity = parseInt(document.getElementById("quantity").value);
    let stockQuantity = parseInt(document.getElementById("numStore").innerText);

    if (stockQuantity <= 0) {
        alert("Sản phẩm đã hết hàng!");
        return;
    }

    if (quantity <= 0) {
        alert("Vui lòng chọn số lượng hợp lệ!");
        return;
    }

    if (quantity > stockQuantity) {
        alert("Số lượng đặt hàng vượt quá số lượng trong kho!");
        return;
    }

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

function openModal(productId, image, name, price, description, quantity) {
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
    document.getElementById("numStore").innerText = quantity;
    document.getElementById("numStore").style.color = "red";
    document.getElementById("modal_id").value = productId;

    const quantityInput = document.getElementById("quantity");
    const addButton = document.getElementById("modal_add_button");

    if (quantity <= 0) {
        quantityInput.disabled = true;
        quantityInput.value = 0;
        addButton.disabled = true;
        addButton.innerText = "Hết hàng";
    } else {
        quantityInput.disabled = false;
        quantityInput.value = 1;
        quantityInput.max = quantity;  // Giới hạn số lượng đặt hàng tối đa
        addButton.disabled = false;
        addButton.innerText = "Add to cart";
    }

    let modalElement = new bootstrap.Modal(document.getElementById("modal_box"));
    modalElement.show();
}

