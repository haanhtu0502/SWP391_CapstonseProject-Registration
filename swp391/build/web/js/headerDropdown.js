const logo = document.querySelector('#logo-dropdown');
const dropdownP = document.querySelector('.dropdown-password');

logo.addEventListener('click', (e) =>{
//    console.log("hello");
//    console.log(e.target);    
    dropdownP.classList.toggle('toggle');
//    console.log(dropdownP);
});