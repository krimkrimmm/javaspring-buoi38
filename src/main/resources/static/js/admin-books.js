let books=[
  {id:1,title:"Book 1",author:"Tác giả A",publisher:"NXB 1",publishYear:2020,price:100000,quantity:10,category:"Khoa học"},
  {id:2,title:"Book 2",author:"Tác giả B",publisher:"NXB 2",publishYear:2019,price:150000,quantity:5,category:"Văn học"}
];

function renderTable(){
  const tbody=document.getElementById("bookTable");
  tbody.innerHTML="";
  books.forEach(b=>{
    tbody.innerHTML+=`<tr>
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
function openAddModal()
{
  document.getElementById("modalTitle").innerText="Thêm Sách";
  document.getElementById("bookForm").reset();
  document.getElementById("bookId").value="";
}

function openEditModal(id){
  const b=books.find(x=>x.id===id);
  document.getElementById("modalTitle").innerText="Sửa Sách";
  document.getElementById("bookId").value=b.id;
  document.getElementById("title").value=b.title;
  document.getElementById("author").value=b.author;
  document.getElementById("publisher").value=b.publisher;
  document.getElementById("publishYear").value=b.publishYear;
  document.getElementById("price").value=b.price;
  document.getElementById("quantity").value=b.quantity;
  document.getElementById("category").value=b.category;
  new bootstrap.Modal(document.getElementById('bookModal')).show();
}

document.getElementById("bookForm").addEventListener("submit",function(e){
  e.preventDefault();
  const id=document.getElementById("bookId").value;
  const bookData={
    id:id?parseInt(id):Date.now(),
    title:document.getElementById("title").value,
    author:document.getElementById("author").value,
    publisher:document.getElementById("publisher").value,
    publishYear:parseInt(document.getElementById("publishYear").value),
    price:parseFloat(document.getElementById("price").value),
    quantity:parseInt(document.getElementById("quantity").value),
    category:document.getElementById("category").value
  };
  if(id){
    const index=books.findIndex(x=>x.id==id);
    books[index]=bookData;
  }else{
    books.push(bookData);
  }
  renderTable();
  bootstrap.Modal.getInstance(document.getElementById("bookModal")).hide();
});












function deleteBook(id){
  if(confirm("Bạn có chắc muốn xóa sách này?")){
    books=books.filter(x=>x.id!==id);
    renderTable();
  }
}

renderTable();
