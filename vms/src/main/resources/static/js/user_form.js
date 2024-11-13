document.addEventListener('DOMContentLoaded', function () {
    const createTab = document.getElementById('createTab');
    const findTab = document.getElementById('findTab');
    const deleteTab = document.getElementById('deleteTab');
    
    const createForm = document.getElementById('createForm');
    const findForm = document.getElementById('findForm');
    const deleteForm = document.getElementById('deleteForm');
    
    const createMessage = document.getElementById('createMessage');
    const findMessage = document.getElementById('findMessage');
    const deleteMessage = document.getElementById('deleteMessage');

    function switchTab(activeTab, activeForm) {
        document.querySelectorAll('.tab-button').forEach(tab => tab.classList.remove('active'));
        document.querySelectorAll('.form-content').forEach(form => form.classList.remove('active'));
        activeTab.classList.add('active');
        activeForm.classList.add('active');
    }

    createTab.addEventListener('click', () => switchTab(createTab, createForm));
    findTab.addEventListener('click', () => switchTab(findTab, findForm));
    deleteTab.addEventListener('click', () => switchTab(deleteTab, deleteForm));

    const createUserForm = document.getElementById('createUserForm');
    const findUserForm = document.getElementById('findUserForm');
    const deleteUserForm = document.getElementById('deleteUserForm');

    // Base64 encode username:password for Basic Authentication
    const username = 'admin';
    const password = 'admin123';
    const credentials = btoa(username + ':' + password);  // Base64 encode

    // Create User Form Submission
    createUserForm.addEventListener('submit', function (event) {
        event.preventDefault();
        
        const formData = new FormData(createUserForm);
        const userData = {
            fullName: formData.get('fullName'),
            email: formData.get('email'),
            phoneNumber: formData.get('phone'),
            address: formData.get('address'),
            emergencyContacts: [
                {
                    name: formData.get('emergencyContact').split(",")[0].trim(),
                    phone: formData.get('phone').trim()
                }
            ],
            preferredTeams: formData.get('preferredTeams').split(",").map(team => team.trim()),
            availability: formData.get('availability'),
            skills: formData.get('skills').split(",").map(skill => skill.trim()),
            additionalComments: formData.get('comments')
        };

        fetch('/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic ' + credentials,  // Basic Auth header
            },
            body: JSON.stringify(userData)
        })
        .then(response => response.json())
        .then(data => {
            createMessage.textContent = `User ${data.fullName} created successfully!`;
            createMessage.style.color = 'green';
            createUserForm.reset();
        })
        .catch(error => {
            createMessage.textContent = 'Error creating user.';
            createMessage.style.color = 'red';
        });
    });

    // Find User Form Submission
    findUserForm.addEventListener('submit', function (event) {
        event.preventDefault();
        
        const userId = document.getElementById('findId').value;
        
        fetch(`/api/users/${userId}`, {
            headers: {
                'Authorization': 'Basic ' + credentials  // Basic Auth header
            }
        })
            .then(response => response.json())
            .then(data => {
                if (data) {
                    findMessage.textContent = `User found: ${data.fullName}`;
                    findMessage.style.color = 'green';
                } else {
                    findMessage.textContent = 'User not found.';
                    findMessage.style.color = 'red';
                }
            })
            .catch(error => {
                findMessage.textContent = 'Error finding user.';
                findMessage.style.color = 'red';
            });

        findUserForm.reset();
    });

    // Delete User Form Submission
    deleteUserForm.addEventListener('submit', function (event) {
        event.preventDefault();
        
        const userId = document.getElementById('deleteId').value;
        
        fetch(`/api/users/${userId}`, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Basic ' + credentials  // Basic Auth header
            }
        })
        .then(response => {
            if (response.status === 204) {
                deleteMessage.textContent = `User with ID ${userId} has been deleted.`;
                deleteMessage.style.color = 'green';
            } else {
                deleteMessage.textContent = 'User not found.';
                deleteMessage.style.color = 'red';
            }
        })
        .catch(error => {
            deleteMessage.textContent = 'Error deleting user.';
            deleteMessage.style.color = 'red';
        });

        deleteUserForm.reset();
    });
    
    switchTab(createTab, createForm);
});
