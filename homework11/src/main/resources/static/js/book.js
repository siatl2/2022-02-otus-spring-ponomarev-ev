Vue.component('book-add-form', {
    props: ['authors', 'genres'],
    data(){
        return {
            book: {"id":0,"name":"","author":{"id":0},"genre":{"id":0}}
        }
    },
    template:
    '<div class="container" mt-5 v-show="this.$root.activeForm==&quot;book-add&quot;">'+
        '<h3 class="h3 mt-5">'+
            'Add book'+
        '</h3><br>'+
        '<form>'+
            '<a title="back to list books" @click="hideThisChapter" href="#" class="btn btn-primary" role="button">'+
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"'+
                     'class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">'+
                    '<path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>'+
                '</svg>'+
                'Back to list books'+
            '</a>'+
            '<button title="save changes" @click="saveChanges" type="submit" class="btn btn-primary shadow-none ms-5" role="button">'+
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"'+
                     'class="bi bi-cloud-upload-fill" viewBox="0 0 16 16">'+
                    '<path fill-rule="evenodd"'+
                          'd="M8 0a5.53 5.53 0 0 0-3.594 1.342c-.766.66-1.321 1.52-1.464 2.383C1.266 4.095 0 5.555 0 7.318 0 9.366 1.708 11 3.781 11H7.5V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11h4.188C14.502 11 16 9.57 16 7.773c0-1.636-1.242-2.969-2.834-3.194C12.923 1.999 10.69 0 8 0zm-.5 14.5V11h1v3.5a.5.5 0 0 1-1 0z"/>'+
                '</svg>'+
                'Save changes'+
            '</button>'+
            '<div class="mb-3 mt-2">'+
                '<label for="id" class="form-label">Id</label>'+
                '<input id="id" type="text" v-model="book.id" disabled class="col-sm-6 form-control"'+
                       'aria-label="Disabled input example">'+
            '</div>'+
            '<div class="mb-3 mt-1">'+
                '<label for="name" class="form-label">Name</label>'+
                '<td>'+
                    '<input id="name" type="text" v-model="book.name" required class="col-sm-6 form-control"'+
                           'aria-label="default input example">'+
                    '<div class="invalid-feedback">'+
                        'Please, enter name.'+
                    '</div>'+
                '</td>'+
            '</div>'+
            '<div class="mb-3 mt-1">'+
                '<label for="author" class="form-label">Author</label>'+
                '<select id="author" v-model="book.author.id" class="col-sm-6 form-select" aria-label="Default select example" required>'+
                    '<option v-for="author in authors" :key="author.id" v-bind:value="author.id"> {{author.name}} </option>'+
                '</select>'+
            '</div>'+
            '<div class="mb-3 mt-1">'+
                '<label for="genre" class="form-label">Genre</label>'+
                '<select id="genre" v-model="book.genre.id" class="col-sm-6 form-select" aria-label="Default select example" required>'+
                    '<option v-for="genre in genres" :key="genre.id" v-bind:value="genre.id"> {{genre.name }} </option>'+
                '</select>'+
            '</div>'+
        '</form>'+
    '</div>',
    methods:{
        hideThisChapter(){
            this.book={"id":0,"name":"","author":{"id":0},"genre":{"id":0}};
            this.$root.activeForm="book-list";
        },
        saveChanges(){
            if (this.book.name!="" && this.book.author.id>0 && this.book.genre.id>0) {
                let postData={
                    id: this.book.id,
                    name: this.book.name,
                    author: {
                        id: this.book.author.id
                    },
                    genre: {
                        id: this.book.genre.id
                    }
                };
                let options = {
                    headers: { 'Content-Type': 'application/json' }
                    , url: '/books'
                    , method: 'post'
                    , data: JSON.stringify(postData)
                }

                this.$root.addBook(options);
                this.book={"id":0,"name":"","author":{"id":0},"genre":{"id":0}};
                this.$root.activeForm="book-list";
            }
        }
    }
})
Vue.component('book-edit-form', {
    props: ['book', 'authors', 'genres'],
    template:
    '<div class="container" mt-5 v-show="this.$root.activeForm==&quot;book-edit&quot;">'+
        '<h3 class="h3 mt-5">'+
            'Edit book #'+
            '<span> {{book.id}} </span>'+
            ':'+
            '<span> {{book.name}} </span>'+
        '</h3><br>'+
        '<form>'+
            '<a title="back to list books" @click="hideThisChapter" href="#" class="btn btn-primary" role="button">'+
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"'+
                     'class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">'+
                    '<path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>'+
                '</svg>'+
                'Back to list books'+
            '</a>'+
            '<button title="save changes" @click="saveChanges" type="submit" class="btn btn-primary shadow-none ms-5" role="button">'+
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"'+
                     'class="bi bi-cloud-upload-fill" viewBox="0 0 16 16">'+
                    '<path fill-rule="evenodd"'+
                          'd="M8 0a5.53 5.53 0 0 0-3.594 1.342c-.766.66-1.321 1.52-1.464 2.383C1.266 4.095 0 5.555 0 7.318 0 9.366 1.708 11 3.781 11H7.5V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11h4.188C14.502 11 16 9.57 16 7.773c0-1.636-1.242-2.969-2.834-3.194C12.923 1.999 10.69 0 8 0zm-.5 14.5V11h1v3.5a.5.5 0 0 1-1 0z"/>'+
                '</svg>'+
                'Save changes'+
            '</button>'+
            '<div class="mb-3 mt-2">'+
                '<label for="id" class="form-label">Id</label>'+
                '<input id="id" type="text" v-model="book.id" disabled class="col-sm-6 form-control"'+
                       'aria-label="Disabled input example">'+
            '</div>'+
            '<div class="mb-3 mt-1">'+
                '<label for="name" class="form-label">Name</label>'+
                '<td>'+
                    '<input id="name" type="text" v-model="book.name" required class="col-sm-6 form-control"'+
                           'aria-label="default input example">'+
                    '<div class="invalid-feedback">'+
                        'Please, enter name.'+
                    '</div>'+
                '</td>'+
            '</div>'+
            '<div class="mb-3 mt-1">'+
                '<label for="author" class="form-label">Author</label>'+
                '<select id="author" v-model="book.author.id" class="col-sm-6 form-select" aria-label="Default select example" required>'+
                    '<option v-for="author in authors" :key="author.id" v-bind:value="author.id"> {{author.name}} </option>'+
                '</select>'+
            '</div>'+
            '<div class="mb-3 mt-1">'+
                '<label for="genre" class="form-label">Genre</label>'+
                '<select id="genre" v-model="book.genre.id" class="col-sm-6 form-select" aria-label="Default select example" required>'+
                    '<option v-for="genre in genres" :key="genre.id" v-bind:value="genre.id"> {{genre.name }} </option>'+
                '</select>'+
            '</div>'+
        '</form>'+
    '</div>',
    methods:{
        hideThisChapter(){
            this.$root.activeForm="book-list";
        },
        saveChanges(){
            if (this.book.name!="" && this.book.author.id>0 && this.book.genre.id>0) {
                let postData={
                    id: this.book.id,
                    name: this.book.name,
                    author: {
                        id: this.book.author.id
                    },
                    genre: {
                        id: this.book.genre.id
                    }
                };
                let options = {
                    headers: { 'Content-Type': 'application/json' }
                    , url: '/books'
                    , method: 'put'
                    , data: JSON.stringify(postData)
                }
                this.$root.updateBook(options);
                this.$root.activeForm="book-list";
            }
        }
    }
})

Vue.component('comment-list-form',{
    props: ['book_name','comments'],
    template:
        '<div class="container mt-5" v-show="this.$root.activeForm==&quot;comment-list&quot;">'+
            '<h3 class="h3">'+
                'Comments on book'+
                '<span>'+
                ' {{ book_name }} '+
                '</span>'+
            '</h3><br>'+
            '<a title="back to list books" @click="hideThisChapter" class="btn btn-primary" role="button">'+
                '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"'+
                     'class="bi bi-arrow-left-circle-fill" viewBox="0 0 16 16">'+
                    '<path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"/>'+
                '</svg>'+
                'Back to list books'+
            '</a>'+
            '<table class="table table-striped">'+
                '<thead>'+
                '<tr>'+
                    '<th>name</th>'+
                '</tr>'+
                '</thead>'+
                    '<tbody>'+
                        '<tr v-for="comment in comments" :key="comment.id">'+
                            '<td> {{ comment.name }}  </td>'+
                        '</tr>'+
                    '</tbody>'+
            '</table>'+
        '</div>',
    methods:{
        hideThisChapter(){
            this.$root.activeForm="book-list";
        }
    }
})

Vue.component('book-header', {
    template:
        '<div>'+
            '<h3 class="h3"> Books </h3>'+
            '<br>'+
                '<a title="add book" @click="onChangeChapterAdd" href="#" class="btn btn-primary" role="button">'+
                    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"'+
                         'class="bi bi-plus-circle-fill" viewBox="0 0 16 16">'+
                        '<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v3h-3a.5.5 0 0 0 0 1h3v3a.5.5 0 0 0 1 0v-3h3a.5.5 0 0 0 0-1h-3v-3z"/>'+
                    '</svg>'+
                    'Add book'+
                '</a>'+
        '</div>',
    methods:{
        onChangeChapterAdd(){
            this.$root.activeForm="book-add";
        }
    }
})

Vue.component('book-row', {
    props: ['book','showAdd'],
    template:
        '<tr>'+
            '<td>' +
                '<a href="#" title="delete book" @click="deleteBook(book.id)" class="btn btn-primary"' +
                   'role="button">' +
                    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"' +
                         'class="bi bi-x-circle-fill bg-red" viewBox="0 0 16 16">' +
                        '<path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>' +
                    '</svg>' +
                    'Delete book' +
                '</a>' +
            '</td>' +
            '<td>' +
                '<a title="edit" href="#" @click="onChangeChapterEdit">' +
                    ' {{ book.id }} ' +
                '</a>' +
            '</td>' +
            '<td>' +
                '<a title="edit" href="#" @click="onChangeChapterEdit">' +
                    ' {{ book.name }} '+
                '</a>' +
            '</td>' +
            '<td>' +
                '<a title="edit" href="#" @click="onChangeChapterEdit">' +
                    '{{ book.author.name }}' +
                '</a>' +
            '</td>' +
            '<td>' +
                '<a title="edit" href="#" @click="onChangeChapterEdit">' +
                    '{{ book.genre.name }}' +
                '</a>' +
            '</td>' +
            '<td>' +
                '<a title="view comments" v-on:click="showComment(book.id,book.name)"' +
                   'class="btn btn-primary" role="button">' +
                    '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"' +
                         'class="bi bi-menu-up" viewBox="0 0 16 16">' +
                        '<path d="M7.646 15.854a.5.5 0 0 0 .708 0L10.207 14H14a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H2a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h3.793l1.853 1.854zM1 9V6h14v3H1zm14 1v2a1 1 0 0 1-1 1h-3.793a1 1 0 0 0-.707.293l-1.5 1.5-1.5-1.5A1 1 0 0 0 5.793 13H2a1 1 0 0 1-1-1v-2h14zm0-5H1V3a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v2zM2 11.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 0-1h-8a.5.5 0 0 0-.5.5zm0-4a.5.5 0 0 0 .5.5h11a.5.5 0 0 0 0-1h-11a.5.5 0 0 0-.5.5zm0-4a.5.5 0 0 0 .5.5h6a.5.5 0 0 0 0-1h-6a.5.5 0 0 0-.5.5z"/>' +
                    '</svg>' +
                    'View comments' +
                '</a>' +
            '</td>'+
        '</tr>',
    methods:{
        showComment(bookId, bookName){
            this.$root.fillComment(bookId);
            this.$root.comment_book_name=bookName;
            this.$root.activeForm="comment-list";
        },
        deleteBook(bookId){
            this.$root.deleteBook(bookId);
        },
        onChangeChapterEdit(){
            app_book.edit_book={"id":this.book.id,"name":this.book.name,"author":{"id":this.book.author.id},"genre":{"id":this.book.genre.id}};
            this.$root.activeForm="book-edit";
        }
    }
})

Vue.component('book-list', {
    props: ['books'],
    template:
        '<table class="table table-striped">'+
            '<thead>'+
                '<tr>'+
                    '<th></th>'+
                    '<th>id</th>'+
                    '<th>name</th>'+
                    '<th>author</th>'+
                    '<th>genre</th>'+
                    '<th></th>'+
                '</tr>'+
            '</thead>'+
            '<tbody>'+
                '<book-row v-for="book in books" :key="book.id" :book="book"/>'+
            '</tbody>'+
        '</table>'
})

Vue.component('book-list-form',{
    props: ['books'],
    template:
        '<div v-show="this.$root.activeForm==&quot;book-list&quot;">'+
            '<book-header />'+
            '<book-list :books="books"/>'+
        '</div>'
})

var app_book = new Vue({
el: '#app_book',
template:
    '<div>'+
        '<book-add-form :authors="authors.data" :genres="genres.data" />' +
        '<book-edit-form :book="edit_book" :authors="authors.data" :genres="genres.data" />'+
        '<comment-list-form :book_name="comment_book_name" :comments="comments.data" />' +
        '<book-list-form :books="books.data" />'+
    '</div>',
data(){
    return{
        books: [],
        edit_book: {"id":0,"name":"","author":{"id":0},"genre":{"id":0}},
        authors: [],
        genres: [],
        comments: [],
        comment_book_name: "Something book",
        activeForm: "book-list"
    };
},
mounted(){
    axios
      .get('/books')
      .then(response => (this.books = response));
    axios
      .get('/authors')
      .then(response => (this.authors = response));
    axios
      .get('/genres')
      .then(response => (this.genres = response));
},
methods:{
    mainMethod(message){
        alert('Hi ' + message);
    },
    addBook(message){
        axios(message)
                .then((response) => {
                    this.books.data.push(response.data);
                });
    },
    updateBook(message){
        axios(message)
                .then((response) => {
                    let index = this.books.data.findIndex(book => book.id==response.data.id);
                    this.books.data.splice(index, 1, response.data);
                });
    },
    deleteBook(bookId){
        axios.delete('/books/' + bookId)
                .then((response) => {
                    let index = this.books.data.findIndex(book => book.id==bookId);
                    this.books.data.splice(index, 1);
                });
    },
    fillComment(bookId){
    axios
      .get('/comments/' + bookId)
      .then(response => (this.comments = response));
    }
}
});
