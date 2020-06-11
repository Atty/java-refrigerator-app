let lists = document.querySelectorAll(".js-list");

function addText() {
    lists.forEach( list => {
        if(list.childNodes.length < 2){
            list.insertAdjacentHTML('afterbegin', '<p class="empty-text">Пусто</p>')
        }
    })
}
addText();