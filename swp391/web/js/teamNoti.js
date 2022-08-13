/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

createToast();
function createToast(timeout) {
    
    const toast = document.querySelector('.toast');

    let toastList = document.getElementById('toasts');
    toastList.appendChild(toast);
    timeout = timeout || 2000;
    setTimeout(function () {
        toast.style.animation = 'slide_hide 2s ease';
    }, timeout);

    setTimeout(function () {
        toast.remove();
    }, timeout + 2000);
}