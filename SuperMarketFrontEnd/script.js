// ===== CONFIGURAÇÃO =====
const API_URL = "http://localhost:8080/market";

// Variáveis globais
let produtos = [];
let produtosFiltrados = [];

// ===== ELEMENTOS DOM =====
const searchInput = document.getElementById('searchInput');
const searchBtn = document.getElementById('searchBtn');
const clearSearchBtn = document.getElementById('clearSearchBtn');
const productForm = document.getElementById('productForm');
const editProductForm = document.getElementById('editProductForm');
const productsTableBody = document.getElementById('productsTableBody');
const emptyMessage = document.getElementById('emptyMessage');
const editModal = document.getElementById('editModal');
const closeModal = document.getElementById('closeModal');
const cancelEdit = document.getElementById('cancelEdit');
const refreshBtn = document.getElementById('refreshBtn');
const toast = document.getElementById('toast');

// ===== FUNÇÕES DE API =====
//GET
async function listarProdutos() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`);
        produtos = await response.json();
        produtosFiltrados = [...produtos];
        renderizarTabela();
    } catch (error) {
        console.error('Erro ao listar:', error);
        mostrarToast('Erro ao carregar produtos.', 'error');
    }
}

//POST
async function criarProduto(dadosProduto) {
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dadosProduto)
        });
        if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`);
        const novoProduto = await response.json();
        produtos.push(novoProduto);
        produtosFiltrados = [...produtos];
        renderizarTabela();
        mostrarToast('Produto adicionado!', 'success');
    } catch (error) {
        console.error('Erro ao criar:', error);
        mostrarToast('Erro ao adicionar produto.', 'error');
    }
}

//PUT
async function editarProduto(id, dadosAtualizados) {
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dadosAtualizados)
        });
        if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`);
        const atualizado = await response.json();
        const index = produtos.findIndex(p => p.id === id);
        if (index !== -1) produtos[index] = atualizado;
        produtosFiltrados = [...produtos];
        renderizarTabela();
        mostrarToast('Produto atualizado!', 'success');
    } catch (error) {
        console.error('Erro ao editar:', error);
        mostrarToast('Erro ao atualizar.', 'error');
    }
}

//DELETE
async function deletarProduto(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`);
        produtos = produtos.filter(p => p.id !== id);
        produtosFiltrados = [...produtos];
        renderizarTabela();
        mostrarToast('Produto removido!', 'success');
    } catch (error) {
        console.error('Erro ao deletar:', error);
        mostrarToast('Erro ao remover.', 'error');
    }
}

//GET/NAME
async function buscarProduto(nome) {
    try {
        if (!nome || nome.trim() === "") {
            produtosFiltrados = [...produtos];
            renderizarTabela();
            return;
        }
        const response = await fetch(`${API_URL}/search?name=${encodeURIComponent(nome)}`);
        if (!response.ok) throw new Error(`Erro HTTP: ${response.status}`);
        produtosFiltrados = await response.json();
        renderizarTabela();
        mostrarToast(`${produtosFiltrados.length} produto(s) encontrado(s)`, 'success');
    } catch (error) {
        console.error('Erro ao buscar:', error);
        mostrarToast('Erro na busca.', 'error');
    }
}

// ===== INTERFACE =====
function renderizarTabela() {
    productsTableBody.innerHTML = '';
    if (produtosFiltrados.length === 0) {
        emptyMessage.style.display = 'block';
        return;
    }
    emptyMessage.style.display = 'none';
    produtosFiltrados.forEach(produto => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>#${produto.id}</td>
            <td>${produto.name}</td>
            <td><span class="quantity ${produto.quantity < 10 ? 'low-stock' : ''}">${produto.quantity} un.</span></td>
            <td>R$ ${produto.price.toFixed(2)}</td>
            <td>${produto.description || '-'}</td>
            <td>${produto.date || '-'}</td>
            <td>
                <button onclick="abrirModalEdicao(${produto.id})" class="btn btn-success btn-small">✏️ Editar</button>
                <button onclick="confirmarDelecao(${produto.id})" class="btn btn-danger btn-small">🗑️ Deletar</button>
            </td>
        `;
        productsTableBody.appendChild(tr);
    });
}

function mostrarToast(msg, tipo = 'success') {
    toast.textContent = msg;
    toast.className = `toast ${tipo} show`;
    setTimeout(() => toast.classList.remove('show'), 3000);
}

function abrirModalEdicao(id) {
    const produto = produtos.find(p => p.id === id);
    if (!produto) return;
    document.getElementById('editProductId').value = produto.id;
    document.getElementById('editProductName').value = produto.name;
    document.getElementById('editProductQuantity').value = produto.quantity;
    document.getElementById('editProductPrice').value = produto.price;
    document.getElementById('editProductDescription').value = produto.description || '';
    document.getElementById('editProductDate').value = produto.date || new Date().toISOString().split("T")[0];
    editModal.style.display = 'block';
    document.body.style.overflow = 'hidden';
}

function fecharModal() {
    editModal.style.display = 'none';
    document.body.style.overflow = 'auto';
    editProductForm.reset();
}

function confirmarDelecao(id) {
    const produto = produtos.find(p => p.id === id);
    if (produto && confirm(`Tem certeza que deseja deletar "${produto.name}"?`)) {
        deletarProduto(id);
    }
}

// ===== EVENTOS =====
productForm.addEventListener('submit', async e => {
    e.preventDefault();
    const name = document.getElementById('productName').value.trim();
    const quantity = parseInt(document.getElementById('productQuantity').value);
    const price = parseFloat(document.getElementById('productPrice').value);
    const description = document.getElementById('productDescription').value.trim();
    const date = document.getElementById('productDate').value || new Date().toISOString().split("T")[0];
    if (!name || quantity < 0 || price < 0) {
        mostrarToast('Preencha os campos corretamente!', 'warning');
        return;
    }
    await criarProduto({ name, quantity, price, description, date });
    productForm.reset();
});

editProductForm.addEventListener('submit', async e => {
    e.preventDefault();
    const id = parseInt(document.getElementById('editProductId').value);
    const name = document.getElementById('editProductName').value.trim();
    const quantity = parseInt(document.getElementById('editProductQuantity').value);
    const price = parseFloat(document.getElementById('editProductPrice').value);
    const description = document.getElementById('editProductDescription').value.trim();
    const date = document.getElementById('editProductDate').value || new Date().toISOString().split("T")[0];
    if (!name || quantity < 0 || price < 0) {
        mostrarToast('Preencha os campos corretamente!', 'warning');
        return;
    }
    await editarProduto(id, { name, quantity, price, description, date });
    fecharModal();
});

searchBtn.addEventListener('click', () => buscarProduto(searchInput.value));
clearSearchBtn.addEventListener('click', () => { searchInput.value = ''; listarProdutos(); });
closeModal.addEventListener('click', fecharModal);
cancelEdit.addEventListener('click', fecharModal);
editModal.addEventListener('click', e => { if (e.target === editModal) fecharModal(); });
refreshBtn.addEventListener('click', listarProdutos);

// ===== INIT =====
document.addEventListener('DOMContentLoaded', listarProdutos);