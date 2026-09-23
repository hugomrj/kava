// src/main/resources/static/js/menu-ui.js
// src/main/resources/static/js/menu-ui.js
(function() {
    'use strict';

    // 1. ELEMENTOS DEL DOM
    const mobileBtn = document.querySelector('.mobile-toggle');
    const sidebarCloseBtn = document.querySelector('.sidebar-close');
    const sidebar = document.querySelector('.sidebar');
    const overlay = document.querySelector('.mobile-overlay');
    const bellBtn = document.getElementById('bellBtn');

    let isMobile = window.innerWidth <= 900;

    // 2. FUNCIONES DEL DRAWER MÓVIL
    function checkMobile() {
        isMobile = window.innerWidth <= 900;
        if (!isMobile) {
            closeDrawer();
        }
    }

    function openDrawer() {
        if (!sidebar || !overlay) return;
        sidebar.classList.add('mobile-open');
        overlay.classList.add('active');
        document.body.style.overflow = 'hidden'; // Bloquea scroll del fondo
    }

    function closeDrawer() {
        if (!sidebar || !overlay) return;
        sidebar.classList.remove('mobile-open');
        overlay.classList.remove('active');
        document.body.style.overflow = ''; // Restaura scroll
    }

    // 3. EVENT LISTENERS DEL DRAWER
    if (mobileBtn) {
        mobileBtn.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();
            openDrawer();
        });
    }

    if (sidebarCloseBtn) {
        sidebarCloseBtn.addEventListener('click', (e) => {
            e.preventDefault();
            closeDrawer();
        });
    }

    if (overlay) {
        overlay.addEventListener('click', closeDrawer);
    }

    document.addEventListener('keydown', (e) => {
        if (e.key === 'Escape' && sidebar?.classList.contains('mobile-open')) {
            closeDrawer();
        }
    });

    window.addEventListener('resize', checkMobile);

    // 4. MENÚ HORIZONTAL: Subrayado del padre al hacer clic en hijo
    document.querySelectorAll('.hnav .submenu button, .hnav .fly button').forEach(btn => {
        btn.addEventListener('click', function() {
            const parentLi = this.closest('li.has-sub');
            if (!parentLi) return;
            const parentBtn = parentLi.querySelector(':scope > .nav-item');
            if (!parentBtn) return;

            document.querySelectorAll('.hnav > li > .nav-item').forEach(b => {
                b.classList.remove('active');
            });
            parentBtn.classList.add('active');
        });
    });

    document.querySelectorAll('.hnav > li > .nav-item').forEach(btn => {
        btn.addEventListener('click', function() {
            document.querySelectorAll('.hnav > li > .nav-item').forEach(b => {
                b.classList.remove('active');
            });
            this.classList.add('active');
        });
    });

    // 5. SIDEBAR: Acordeón y activación
    document.querySelectorAll('.sgroup .grp').forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();
            const group = btn.closest('.sgroup');
            const isOpen = group.classList.contains('open');

            document.querySelectorAll('.sgroup').forEach(g => g.classList.remove('open'));
            if (!isOpen) {
                group.classList.add('open');
            }
        });
    });

    document.querySelectorAll('.sitem:not(.grp)').forEach(btn => {
        btn.addEventListener('click', function(e) {
            e.stopPropagation();
            document.querySelectorAll('.sitem').forEach(b => b.classList.remove('active'));
            this.classList.add('active');
            if (isMobile) {
                setTimeout(closeDrawer, 150);
            }
        });
    });

    if (sidebar) {
        sidebar.addEventListener('click', (e) => {
            e.stopPropagation();
        });
    }

    // 6. EXTRAS: Animación de campana
    if (bellBtn) {
        bellBtn.addEventListener('click', function() {
            this.classList.remove('ring');
            void this.offsetWidth; // Forzar reflow para reiniciar animación
            this.classList.add('ring');
        });
    }

})();