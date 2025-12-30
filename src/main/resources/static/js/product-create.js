function loadCategories() {
    fetch('/api/categories')
        .then(res => res.json())
        .then(res => {
            const select = document.getElementById('categoryId');
            select.innerHTML = `<option value="">-- Chọn loại --</option>`;
            res.data.forEach(c => {
                select.innerHTML += `<option value="${c.id}">${c.name}</option>`;
            });
        });
}

function create() {
    const name = document.getElementById('name').value;
    const price = document.getElementById('price').value;
    const categoryId = document.getElementById('categoryId').value;

    if (!name || !price || !categoryId) {
        alert('Không được để trống');
        return;
    }

    if (name.length < 5 || name.length > 50) {
        alert('Tên sản phẩm phải từ 5 đến 50 ký tự');
        return;
    }

    if (price < 100000) {
        alert('Giá phải >= 100.000');
        return;
    }

    fetch('/api/products', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({name, price, categoryId})
    }).then(() => location.href = '/products');
}

loadCategories();
