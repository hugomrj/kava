// src/main/resources/static/js/ux.js
document.addEventListener('DOMContentLoaded', () => {
    UX.initToasts();
});

window.UX = window.UX || {};

window.UX = {
    /**
     * SISTEMA DE TOASTS
     * Auto-elimina los toasts existentes al cargar la página.
     */
    initToasts: function() {
        const toasts = document.querySelectorAll('.toast');
        toasts.forEach(toast => {
            setTimeout(() => {
                toast.style.opacity = '0';
                toast.style.transform = 'translateX(100%)';
                toast.style.transition = 'all 0.4s ease';
                setTimeout(() => {
                    if (toast.parentElement) toast.remove();
                }, 400);
            }, 4000);
        });
    },

    /**
     * Muestra un toast dinámicamente (útil para HTMX o AJAX)
     */
    showToast: function(message, type = 'success', title = '') {
        let container = document.querySelector('.toast-container');
        if (!container) {
            container = document.createElement('div');
            container.className = 'toast-container';
            document.body.appendChild(container);
        }

        const iconSvg = type === 'error'
            ? '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><path d="M12 8v4M12 16h.01"/></svg>'
            : '<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="m5 12.5 4.5 4.5L19 7"/></svg>';

        const toastTitle = title || (type === 'error' ? 'Error' : 'Éxito');

        const toastHTML = `
            <div class="toast ${type}">
                <div class="toast-icon">${iconSvg}</div>
                <div class="toast-content">
                    <div class="toast-title">${toastTitle}</div>
                    <div class="toast-message">${message}</div>
                </div>
                <button class="toast-close" onclick="this.parentElement.remove()">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M18 6 6 18M6 6l12 12"/></svg>
                </button>
            </div>
        `;

        container.insertAdjacentHTML('beforeend', toastHTML);
        const newToast = container.lastElementChild;

        setTimeout(() => {
            newToast.style.opacity = '0';
            newToast.style.transform = 'translateX(100%)';
            newToast.style.transition = 'all 0.4s ease';
            setTimeout(() => newToast.remove(), 400);
        }, 4000);
    }
};