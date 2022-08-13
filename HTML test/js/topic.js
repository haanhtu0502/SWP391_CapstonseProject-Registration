// toggle filter
const filter =document.querySelector('.topic__filter');
const dropdown =document.querySelector('.dropdown');

filter.addEventListener('click',() => {
    dropdown.classList.toggle('toggle');
})

// ************************