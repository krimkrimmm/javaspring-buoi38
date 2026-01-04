// ===== Dữ liệu mẫu =====
let currentRole = "Reader"; // Reader/Librarian/Admin

let users = [
  {email:"reader@example.com",password:"123456",role:"Reader",name:"Người đọc"},
  {email:"librarian@example.com",password:"123456",role:"Librarian",name:"Thủ thư"},
  {email:"admin@example.com",password:"123456",role:"Admin",name:"Admin"}
];

let books = [
  {id:1,title:"Book 1",author:"Tác giả A",publisher:"NXB 1",publishYear:2020,price:100000,quantity:10,category:"Khoa học"},
  {id:2,title:"Book 2",author:"Tác giả B",publisher:"NXB 2",publishYear:2019,price:150000,quantity:5,category:"Văn học"}
];

let cart = [];

let borrows = [
  {user:"reader@example.com",book:"Book 1",quantity:1,status:"Đang mượn"},
  {user:"reader@example.com",book:"Book 2",quantity:2,status:"Đã trả"}
];

// ===== Quản lý bảng sách (Admin/Librarian) =====
function renderBooksTable(){
  const tbody = document.getElementById("bookTable");
  if(!tbody) return;
  tbody.innerHTML = "";
  books.forEach(b=>{
    tbody.innerHTML += `<tr>
        <td>${b.id}</td>
        <td>${b.title}</td>
        <td>${b.author}</td>
        <td>${b.publisher}</td>
        <td>${b.publishYear}</td>
        <td>${b.price}</td>
        <td>${b.quantity}</td>
        <td>${b.category}</td>
        <td>
          <button class="btn btn-sm btn-warning me-1" onclick="openEditModal(${b.id})">Sửa</button>
          <button class="btn btn-sm btn-danger" onclick="deleteBook(${b.id})">Xóa</button>
        </td>
    </tr>`;
  });
}

function openAddModal(){
  if(!document.getElementById("bookForm")) return;
  document.getElementById("modalTitle").innerText="Thêm Sách";
  document.getElementById("bookForm").reset();
  document.getElementById("bookId").value="";
}

function openEditModal(id){
  const b = books.find(x=>x.id===id);
  document.getElementById("modalTitle").innerText="Sửa Sách";
  document.getElementById("bookId").value=b.id;
  document.getElementById("title").value=b.title;
  document.getElementById("author").value=b.author;
  document.getElementById("publisher").value=b.publisher;
  document.getElementById("publishYear").value=b.publishYear;
  document.getElementById("price").value=b.price;
  document.getElementById("quantity").value=b.quantity;
  document.getElementById("category").value=b.category;
}

if(document.getElementById("bookForm")){
  document.getElementById("bookForm").addEventListener("submit", function(e){
    e.preventDefault();
    const id = document.getElementById("bookId").value;
    const bookData = {
      id: id ? parseInt(id) : Date.now(),
      title: document.getElementById("title").value,
      author: document.getElementById("author").value,
      publisher: document.getElementById("publisher").value,
      publishYear: parseInt(document.getElementById("publishYear").value),
      price: parseFloat(document.getElementById("price").value),
      quantity: parseInt(document.getElementById("quantity").value),
      category: document.getElementById("category").value
    };
    if(id){
      const index = books.findIndex(x=>x.id==id);
      books[index]=bookData;
    } else {
      books.push(bookData);
    }
    renderBooksTable();
    bootstrap.Modal.getInstance(document.getElementById("bookModal")).hide();
  });
}

function deleteBook(id){
  if(confirm("Bạn có chắc muốn xóa sách này?")){
    books = books.filter(x=>x.id!==id);
    renderBooksTable();
  }
}

// ===== Quản lý người dùng (Admin) =====
function renderUsers(){
  const tbody = document.getElementById("userTable");
  if(!tbody) return;
  tbody.innerHTML="";
  users.forEach((u,index)=>{
    tbody.innerHTML += `<tr>
        <td>${u.email}</td>
        <td>${u.name || '-'}</td>
        <td>${u.role}</td>
        <td>
            <button class="btn btn-sm btn-warning me-1" onclick="toggleRole(${index})">Đổi role</button>
            <button class="btn btn-sm btn-danger" onclick="deleteUser(${index})">Xóa</button>
        </td>
    </tr>`;
  });
}

function toggleRole(index){
  if(confirm("Bạn có muốn đổi role cho người dùng này?")){
    users[index].role = users[index].role==="Reader"?"Librarian":"Reader";
    renderUsers();
  }
}

function deleteUser(index){
  if(confirm("Bạn có chắc muốn xóa người dùng này?")){
    users.splice(index,1);
    renderUsers();
  }
}

// ===== Quản lý mượn/trả sách (Admin/Librarian) =====
function renderBorrows(){
  const tbody = document.getElementById("borrowTable");
  if(!tbody) return;
  tbody.innerHTML="";
  borrows.forEach((b,index)=>{
    tbody.innerHTML += `<tr>
        <td>${b.user}</td>
        <td>${b.book}</td>
        <td>${b.quantity}</td>
        <td>${b.status}</td>
        <td>
            <button class="btn btn-sm btn-success me-1" onclick="toggleStatus(${index})">Đổi trạng thái</button>
            <button class="btn btn-sm btn-danger" onclick="deleteBorrow(${index})">Xóa</button>
        </td>
    </tr>`;
  });
}

function toggleStatus(index){
  borrows[index].status = borrows[index].status==="Đang mượn"?"Đã trả":"Đang mượn";
  renderBorrows();
}

function deleteBorrow(index){
  if(confirm("Bạn có chắc muốn xóa giao dịch này?")){
    borrows.splice(index,1);
    renderBorrows();
  }
}

// ===== Quản lý giỏ sách (Reader) =====
function addToCart(bookTitle){
  const book = books.find(b=>b.title===bookTitle);
  if(book){
    const item = cart.find(c=>c.book.title===bookTitle);
    if(item) item.quantity+=1;
    else cart.push({book,quantity:1});
    alert(`Đã thêm sách '${bookTitle}' vào giỏ!`);
    renderCart();
  }
}

function renderCart(){
  const container = document.getElementById("cartContainer");
  if(!container) return;
  container.innerHTML="";
  cart.forEach((item,index)=>{
    container.innerHTML += `<div class="cart-item">
        <span>${item.book.title} (x${item.quantity})</span>
        <div>
            <button onclick="removeCart(${index})" class="btn btn-sm btn-danger">Xóa</button>
        </div>
    </div>`;
  });
}

function removeCart(index){
  cart.splice(index,1);
  renderCart();
}

// ===== Khởi tạo =====
renderBooksTable();
renderUsers();
renderBorrows();
renderCart();


