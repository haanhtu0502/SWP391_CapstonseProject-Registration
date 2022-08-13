const filter = document.querySelector('.topic__filter');
const dropdown = document.querySelector('.dropdown1');

filter.addEventListener('click', () => {
    dropdown.classList.toggle('toggle');
});

const semester = document.querySelector('.semester');
const dropdown2 = document.querySelector('.dropdown2');

semester.addEventListener('click', () => {
    dropdown2.classList.toggle('toggle');
});