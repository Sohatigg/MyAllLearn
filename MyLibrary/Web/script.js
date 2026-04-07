// Состояние приложения
let currentUser = null;
let entries = [];

// Показать форму регистрации
function showRegister() {
    document.getElementById('authSection').style.display = 'none';
    document.getElementById('registerSection').style.display = 'block';
}

// Показать форму входа
function showLogin() {
    document.getElementById('registerSection').style.display = 'none';
    document.getElementById('authSection').style.display = 'block';
}

// Показать дневник
function showDiary() {
    document.getElementById('authSection').style.display = 'none';
    document.getElementById('registerSection').style.display = 'none';
    document.getElementById('diarySection').style.display = 'block';
    document.getElementById('currentUser').textContent = currentUser;
    loadEntries();
}

// Регистрация
function register() {
    const username = document.getElementById('regUsername').value;
    const password = document.getElementById('regPassword').value;

    if (!username || !password) {
        alert('Заполните все поля!');
        return;
    }

    // Проверяем, есть ли уже такой пользователь
    const users = JSON.parse(localStorage.getItem('users') || '[]');

    if (users.find(u => u.username === username)) {
        alert('Пользователь с таким логином уже существует!');
        return;
    }

    // Сохраняем пользователя
    users.push({
        username: username,
        password: password,
        registrationDate: new Date().toISOString()
    });

    localStorage.setItem('users', JSON.stringify(users));
    alert('Регистрация успешна! Теперь можете войти.');
    showLogin();

    // Очищаем поля
    document.getElementById('regUsername').value = '';
    document.getElementById('regPassword').value = '';
}

// Вход
function login() {
    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    if (!username || !password) {
        alert('Введите логин и пароль!');
        return;
    }

    const users = JSON.parse(localStorage.getItem('users') || '[]');
    const user = users.find(u => u.username === username && u.password === password);

    if (user) {
        currentUser = username;
        showDiary();

        // Загружаем записи пользователя
        const allEntries = JSON.parse(localStorage.getItem('entries') || '[]');
        entries = allEntries.filter(e => e.username === username);

        // Очищаем поля
        document.getElementById('loginUsername').value = '';
        document.getElementById('loginPassword').value = '';
    } else {
        alert('Неверный логин или пароль!');
    }
}

// Выход
function logout() {
    currentUser = null;
    entries = [];
    document.getElementById('diarySection').style.display = 'none';
    document.getElementById('authSection').style.display = 'block';
}

// Добавить запись
function addEntry() {
    const title = document.getElementById('entryTitle').value;
    const content = document.getElementById('entryContent').value;

    if (!title || !content) {
        alert('Заполните заголовок и текст!');
        return;
    }

    const newEntry = {
        username: currentUser,
        title: title,
        content: content,
        date: new Date().toLocaleString()
    };

    entries.push(newEntry);

    // Сохраняем все записи
    const allEntries = JSON.parse(localStorage.getItem('entries') || '[]');
    allEntries.push(newEntry);
    localStorage.setItem('entries', JSON.stringify(allEntries));

    // Очищаем поля
    document.getElementById('entryTitle').value = '';
    document.getElementById('entryContent').value = '';

    loadEntries();
}

// Загрузить и показать записи
function loadEntries() {
    const entriesDiv = document.getElementById('entries');
    entriesDiv.innerHTML = '';

    if (entries.length === 0) {
        entriesDiv.innerHTML = '<p style="color: #999;">У вас пока нет записей</p>';
        return;
    }

    // Показываем в обратном порядке (сначала новые)
    entries.slice().reverse().forEach(entry => {
        const entryDiv = document.createElement('div');
        entryDiv.className = 'entry-item';
        entryDiv.innerHTML = `
            <h4>${entry.title}</h4>
            <p>${entry.content}</p>
            <small>${entry.date}</small>
        `;
        entriesDiv.appendChild(entryDiv);
    });
}