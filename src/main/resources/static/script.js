document.addEventListener('DOMContentLoaded', function() {
    const menuItems = document.querySelectorAll('.menu-item');
    const pages = document.querySelectorAll('[id$="-page"]');
    
    menuItems.forEach(item => {
        item.addEventListener('click', function() {
            // Remove active class from all menu items
            menuItems.forEach(i => i.classList.remove('active'));
            // Add active class to clicked item
            this.classList.add('active');
            
            // Hide all pages
            pages.forEach(page => page.style.display = 'none');
            
            // Show selected page
            const pageId = this.getAttribute('data-page');
            document.getElementById(pageId + '-page').style.display = 'block';
        });
    });
    
    // Patient form toggle
    const addPatientBtn = document.getElementById('add-patient-btn');
    const cancelPatientBtn = document.getElementById('cancel-patient-btn');
    const patientForm = document.getElementById('patient-form');
    
    if (addPatientBtn) {
        addPatientBtn.addEventListener('click', function() {
            patientForm.style.display = 'block';
        });
    }
    
    if (cancelPatientBtn) {
        cancelPatientBtn.addEventListener('click', function() {
            patientForm.style.display = 'none';
        });
    }
    
    // Patient form submission
    const newPatientForm = document.getElementById('new-patient-form');
    if (newPatientForm) {
        newPatientForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // In a real application, you would send this data to your backend
            const formData = {
                firstName: document.getElementById('firstName').value,
                lastName: document.getElementById('lastName').value,
                dob: document.getElementById('dob').value,
                gender: document.getElementById('gender').value,
                email: document.getElementById('email').value,
                phone: document.getElementById('phone').value,
                address: document.getElementById('address').value,
                insurance: document.getElementById('insurance').value
            };
            
            console.log('New patient data:', formData);
            
            // Reset form and hide it
            newPatientForm.reset();
            patientForm.style.display = 'none';
            
            // Here you would typically refresh the patient list
            // For now we'll just show an alert
            alert('Patient added successfully!');
        });
    }
});