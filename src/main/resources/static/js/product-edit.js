const productId = window.location.pathname.split('/').pop();

function loadProduct() {
    fetch(`/api/products/${productId}`)
        .then(res => res.json())
        .then(res => {
            const p = res.data;
            document.getElementById('name').value = p.name;
            document.getElementById('price').value = p.price;
            document.getElementById('category').value = p.categoryName;
            document.getElementById('status').value = p.status;
        });
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
