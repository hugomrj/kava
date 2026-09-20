// src/main/resources/static/js/menu-ui.js

document.addEventListener('DOMContentLoaded', () => {
    const mobileBtn = document.getElementById('mobileMenuBtn');
    const mainNav = document.getElementById('mainNav');
    const closeBtn = document.getElementById('closeMobileMenu');
    const overlay = document.getElementById('mobileOverlay');

    function toggleMenu() {
        mainNav.classList.toggle('mobile-open');
        overlay.classList.toggle('active');
    }

    mobileBtn?.addEventListener('click', (e) => {
        e.stopPropagation();
        toggleMenu();
    });

    closeBtn?.addEventListener('click', toggleMenu);

    overlay?.addEventListener('click', () => {
        mainNav.classList.remove('mobile-open');
        overlay.classList.remove('active');
        document.querySelectorAll('.submenu, .fly, .has-sub, .has-fly').forEach(el =>
            el.classList.remove('mobile-active')
        );
    });

    // Lógica de submenús en móvil
    document.querySelectorAll('.hnav > li > .nav-item').forEach(btn => {
        btn.addEventListener('click', function (e) {
            const parentLi = this.closest('li');
            const submenu = parentLi.querySelector('.submenu');

            document.querySelectorAll('.hnav > li > .nav-item').forEach(b => b.classList.remove('active'));
            this.classList.add('active');

            if (window.innerWidth <= 900 && submenu) {
                e.preventDefault();
                const isActive = parentLi.classList.contains('mobile-active');
                document.querySelectorAll('.hnav > li').forEach(li => {
                    li.classList.remove('mobile-active');
                    li.querySelector('.submenu')?.classList.remove('mobile-active');
                });
                if (!isActive) {
                    parentLi.classList.add('mobile-active');
                    submenu.classList.add('mobile-active');
                }
            }
        });
    });
});