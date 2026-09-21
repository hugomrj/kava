// src/main/resources/static/js/menu-ui.js

(function() {
    'use strict';

    // ═══════════════════════════════════════════════════════════
    // 1. ELEMENTOS DEL DOM Y ESTADO
    // ═══════════════════════════════════════════════════════════
    const mobileBtn = document.getElementById('mobileMenuBtn');
    const sidebarCloseBtn = document.getElementById('sidebarCloseBtn');
    const sidebar = document.querySelector('.sidebar');
    const overlay = document.getElementById('mobileOverlay');
    const bellBtn = document.getElementById('bellBtn');

    let isMobile = window.innerWidth <= 900;

    // ═══════════════════════════════════════════════════════════
    // 2. FUNCIONES DEL DRAWER MÓVIL
    // ═══════════════════════════════════════════════════════════
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

    // ═══════════════════════════════════════════════════════════
    // 3. EVENT LISTENERS DEL DRAWER
    // ═══════════════════════════════════════════════════════════
    if (mobileBtn) {
        mobileBtn.addEventListener('click', (e) => {
            e.stopPropagation();
            openDrawer();
        });
    }

    if (sidebarCloseBtn) {
        sidebarCloseBtn.addEventListener('click', closeDrawer);
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

    // ═══════════════════════════════════════════════════════════
    // 4. MENÚ HORIZONTAL: Subrayado del padre al hacer clic en hijo
    // ═══════════════════════════════════════════════════════════

    // A) Cuando se hace clic en un botón DENTRO del submenú horizontal
    document.querySelectorAll('.hnav .submenu button, .hnav .fly button').forEach(btn => {
        btn.addEventListener('click', function() {
            // Buscar el contenedor padre (li.has-sub)
            const parentLi = this.closest('li.has-sub');
            if (!parentLi) return;

            // Buscar el botón principal de ese padre
            const parentBtn = parentLi.querySelector(':scope > .nav-item');
            if (!parentBtn) return;

            // Quitar la clase 'active' (y la línea verde) de todos los items principales
            document.querySelectorAll('.hnav > li > .nav-item').forEach(b => {
                b.classList.remove('active');
            });

            // Agregar 'active' al padre (esto activa el ::after con la línea verde)
            parentBtn.classList.add('active');
        });
    });

    // B) Cuando se hace clic directamente en un item principal (ej: "Panel general")
    document.querySelectorAll('.hnav > li > .nav-item').forEach(btn => {
        btn.addEventListener('click', function() {
            document.querySelectorAll('.hnav > li > .nav-item').forEach(b => {
                b.classList.remove('active');
            });
            this.classList.add('active');
        });
    });

    // ═══════════════════════════════════════════════════════════
    // 5. SIDEBAR: Acordeón y activación
    // ═══════════════════════════════════════════════════════════

    // Acordeón del sidebar
    document.querySelectorAll('.sgroup .grp').forEach(btn => {
        btn.addEventListener('click', (e) => {
            e.preventDefault();
            e.stopPropagation();

            const group = btn.closest('.sgroup');
            const isOpen = group.classList.contains('open');

            // Cerrar todos los grupos (comportamiento acordeón estricto)
            document.querySelectorAll('.sgroup').forEach(g => g.classList.remove('open'));

            // Abrir solo si estaba cerrado
            if (!isOpen) {
                group.classList.add('open');
            }
        });
    });

    // Activar items del sidebar
    document.querySelectorAll('.sitem:not(.grp)').forEach(btn => {
        btn.addEventListener('click', function(e) {
            e.stopPropagation();

            document.querySelectorAll('.sitem').forEach(b => b.classList.remove('active'));
            this.classList.add('active');

            // Cerrar drawer en móvil después de seleccionar
            if (isMobile) {
                setTimeout(closeDrawer, 150);
            }
        });
    });

    // Prevenir que el clic dentro del sidebar cierre el drawer
    if (sidebar) {
        sidebar.addEventListener('click', (e) => {
            e.stopPropagation();
        });
    }

    // ═══════════════════════════════════════════════════════════
    // 6. EXTRAS: Animación de campana
    // ═══════════════════════════════════════════════════════════
    if (bellBtn) {
        bellBtn.addEventListener('click', function() {
            this.classList.remove('ring');
            void this.offsetWidth; // Forzar reflow para reiniciar animación
            this.classList.add('ring');
        });
    }

})();