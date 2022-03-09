let index = 0;

function displayCategory(category) {
    return `<option value="${category.id}">${category.name}</option>`
}

function getCategory() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/blogs/categories`,
        success: function (data) {
            let content = "";
            for (let i = 0; i < data.length; i++) {
                content += displayCategory(data[i]);
            }
            document.getElementById("category").innerHTML = content;
        }
    });
}

function displayBlog(blog) {
    return `<tr>
                <td>${blog.name}</td>
                <td>${blog.description}</td>
                <td>${blog.category.id}</td>
                <td>${blog.category.name}</td>
                <td><button onclick="showEditBlog(${blog.id})">Edit</button></td>
                <td><button onclick="deleteBlog(${blog.id})">Delete</button></td>
                <td><button onclick="viewBlog(${blog.id})">View</button></td>
            </tr>`
}

function getBlog() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/blogs`,
        success: function (data) {
            let content = `<tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Category Id</th>
                                <th>Category Name</th>
                                <th colspan="3">Action</th>
                            </tr>`;
            for (let i = 0; i < data.length; i++) {
                content += displayBlog(data[i]);
            }
            document.getElementById("blogList").innerHTML = content;
            document.getElementById("form").hidden = true;
        }
    });
}

function viewBlog(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/blogs/${id}`,
        success: function (data) {
            let content = `<tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Category Id</th>
                                <th>Category Name</th>
                                <th colspan="2">Action</th>
                            </tr>
                            <tr>
                                 <td>${data.name}</td>
                                 <td>${data.description}</td>
                                 <td>${data.category.id}</td>
                                 <td>${data.category.name}</td>
                                 <td><button onclick="showEditBlog(${data.id})">Edit</button></td>
                                 <td><button onclick="deleteBlog(${data.id})">Delete</button></td>
                            </tr>`;
            document.getElementById("blogList").innerHTML = content;
            document.getElementById("form").hidden = true;
        }
    });
}

function deleteBlog(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/blogs/${id}`,
        success: function () {
            getBlog();
        }
    });
}

function searchBlog() {
    let search = document.getElementById("search").value;
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/blogs/search?search=${search}`,
        success: function (data) {
            let content = `<tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Category Id</th>
                                <th>Category Name</th>
                                <th colspan="3">Action</th>
                            </tr>`;
            for (let i = 0; i < data.length; i++) {
                content += displayBlog(data[i]);
            }
            document.getElementById('blogList').innerHTML = content;
            document.getElementById('searchForm').reset();
        }
    });
    event.preventDefault();
}

function createBlog() {
    let name = $('#name').val();
    let description = $('#description').val();
    let category = $('#category').val();
    let newBlog = {
        name: name,
        description: description,
        category: {
            id: category
        }
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newBlog),
        url: `http://localhost:8080/api/blogs`,
        success: function () {
            getBlog();
        }
    });
    event.preventDefault();
}

function displayFormCreate() {
    document.getElementById("form").reset();
    document.getElementById("form").hidden = false;
    document.getElementById("form-button").onclick = function () {
        createBlog();
    }
}

function showEditBlog(id) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/api/blogs/${id}`,
        success: function (data) {
            $('#name').val(data.name);
            $('#description').val(data.description);
            $('#category').val(data.category.id);
            index = data.id;
            document.getElementById("form").hidden = false;
            document.getElementById("form-button").onclick = function () {
                editBlog()
            }
        }
    });
}


function editBlog() {
    let name = $('#name').val();
    let description = $('#description').val();
    let category = $('#category').val();
    let editBlog = {
        name: name,
        description: description,
        category: {
            id: category
        }
    };
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        data: JSON.stringify(editBlog),
        url: `http://localhost:8080/api/blogs/${index}`,
        success: function () {
            getBlog();
        }
    });
    event.preventDefault();
}

getBlog()
getCategory()