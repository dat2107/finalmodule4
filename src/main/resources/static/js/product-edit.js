const productId = window.location.pathname.split('/').pop();

function loadProduct() {
    fetch(`/api/products/${productId}`)
        .then(res => res.json())
        .then(res => {
            const p = res.data;

            document.getElementById('name').value = p.name;
            document.getElementById('price').value = p.price;
            document.getElementById('category').value = p.categoryName;

            const statusSelect = document.getElementById('status');
            const updateBtn = document.querySelector('.btn-update');

            statusSelect.value = p.status;

            if (p.status === 'PENDING') {
                // PENDING: ẩn SOLD
                hideOption(statusSelect, 'SOLD');
            }

            if (p.status === 'APPROVED') {
                // APPROVED: ẩn PENDING
                hideOption(statusSelect, 'PENDING');
            }

            if (p.status === 'SOLD') {
                // SOLD: không cho sửa
                statusSelect.disabled = true;
                updateBtn.style.display = 'none';
            }
        });
}

function hideOption(select, value) {
    const option = select.querySelector(`option[value="${value}"]`);
    if (option) option.remove();
}

function updateStatus() {
    const status = document.getElementById('status').value;

    fetch(`/api/products/${productId}/status`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ status })
    })
        .then(res => res.json())
        .then(res => {
            alert(res.message);
            window.location.href = '/products';
        });
}

loadProduct();
