let page = 0;
let totalPages = 0;

function loadCategories() {
    fetch('/api/categories')
        .then(res => res.json())
        .then(res => {
            const select = document.getElementById('categoryId');
            select.innerHTML = `<option value="">-- Loại sản phẩm --</option>`;
            res.data.forEach(c => {
                select.innerHTML += `<option value="${c.id}">${c.name}</option>`;
            });
        });
}

function loadData() {
    const params = new URLSearchParams({
        name: document.getElementById('name').value,
        categoryId: document.getElementById('categoryId').value,
        price: document.getElementById('price').value,
        page: page,
        size: 5
    });

    fetch('/api/products?' + params)
        .then(res => res.json())
        .then(res => {
            const data = res.data;
            totalPages = data.totalPages;

            const tbody = document.getElementById('table-body');
            tbody.innerHTML = '';

            data.items.forEach(p => {
                tbody.innerHTML += `
                    <tr>
                        <td><input type="checkbox" class="row-check" value="${p.id}"></td>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td>${p.categoryName}</td>
                        <td>${p.status}</td>
                        <td>
                            <a href="/products/edit/${p.id}">
                                <button>Sửa</button>
                            </a>
                        </td>
                    </tr>
                `;
            });

            document.getElementById('page-info').innerText =
                `Trang ${data.page + 1} / ${data.totalPages}`;
        });
}

function deleteSelected() {
    const ids = [...document.querySelectorAll('.row-check:checked')]
        .map(cb => Number(cb.value));

    if (ids.length === 0) {
        alert('Vui lòng chọn sản phẩm');
        return;
    }

    if (!confirm('Bạn có muốn xóa tất cả sản phẩm đã chọn')) return;

    fetch('/api/products', {
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(ids)
    }).then(() => loadData());
}

function search() {
    page = 0;
    loadData();
}

function resetSearch() {
    document.getElementById('name').value = '';
    document.getElementById('categoryId').value = '';
    document.getElementById('price').value = '';
    loadData();
}

function prevPage() {
    if (page > 0) {
        page--;
        loadData();
    }
}

function nextPage() {
    if (page < totalPages - 1) {
        page++;
        loadData();
    }
}

function toggleAll(source) {
    document.querySelectorAll('.row-check').forEach(cb => cb.checked = source.checked);
}

loadCategories();
loadData();
