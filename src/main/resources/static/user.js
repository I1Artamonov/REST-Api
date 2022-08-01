const url = 'http://localhost:8080/api/users'
const header = document.querySelector('#head')
const user = document.querySelector('#User')

fetch(url)
    .then(res => res.json())
    .then(data => {
        header.innerHTML = `<span class="align-middle font-weight-bold mr-1">${data.username}   </span></b>
                <span class="align-middle mr-1">with roles:  </span>
                <span>  ${data.roles.map(role => role.role === 'ROLE_USER' ? 'USER' : 'ADMIN')}</span>`;
        user.innerHTML = `
                                <td>${data.id}</td>
                                <td>${data.username}</td>
                                <td>${data.name}</td>
                                <td>${data.lastName}</td>
                                <td>${data.age}</td>
                                <td>${data.email}</td>
                                <td>${data.roles.map(role => role.role === 'ROLE_USER' ? 'USER' : 'ADMIN')}</td>
                                `;
    })