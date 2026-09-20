// src/main/resources/static/js/dashboard.js

document.addEventListener('DOMContentLoaded', () => {
    // ═══════ DATOS ═══════
    const MONTHS = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'];
    const ING = [62, 58, 70, 66, 74, 82, 78, 88, 84, 92, 86, 97];
    const GAS = [48, 44, 52, 50, 55, 58, 60, 63, 59, 64, 61, 66];
    const AREAS = [
        ['Personal', 42, '#2E9E6B', '€ 77,3 K'],
        ['Operaciones', 26, '#E8A23D', '€ 47,8 K'],
        ['Tecnología', 18, '#51798A', '€ 33,1 K'],
        ['Marketing', 14, '#A8C6C1', '€ 25,8 K']
    ];

    // ═══════ GRÁFICO DE BARRAS ═══════
    function buildBars() {
        const bars = document.getElementById('barsChart');
        if (!bars) return;
        bars.innerHTML = MONTHS.map((m, i) => `
            <div class="pair ${i === 2 ? 'now' : ''}" style="--d:${i * 45}ms">
                <div class="tip">Ingresos €${ING[i]} K · Gastos €${GAS[i]} K</div>
                <div class="cols">
                    <div class="col"><i class="b in" style="--v:${ING[i] / 100}"></i></div>
                    <div class="col"><i class="b out" style="--v:${GAS[i] / 100}"></i></div>
                </div>
                <span class="x">${m}</span>
            </div>`).join('');
    }

    // ═══════ GRÁFICO DE DONA ═══════
    function buildDonut() {
        const svg = document.getElementById('donutChart');
        const legend = document.getElementById('donutLegend');
        if (!svg || !legend) return;
        const C = 2 * Math.PI * 54;
        let acc = 0;
        let segs = '';
        AREAS.forEach(([n, v, c], i) => {
            segs += `<circle cx="70" cy="70" r="54" fill="none" stroke="${c}" stroke-width="17"
                stroke-dasharray="0 ${C}" data-dash="${(v * C / 100).toFixed(1)} ${C.toFixed(1)}"
                stroke-dashoffset="${(-acc * C / 100).toFixed(1)}" transform="rotate(-90 70 70)"
                style="transition:stroke-dasharray .9s cubic-bezier(.25,.8,.3,1) ${0.25 + i * 0.14}s"/>`;
            acc += v;
        });
        svg.innerHTML = segs;
        legend.innerHTML = AREAS.map(([n, v, c, val]) =>
            `<li><i style="background:${c}"></i>${n}<span class="pct">${v} %</span><span class="val">${val}</span></li>`).join('');

        requestAnimationFrame(() => {
            svg.querySelectorAll('circle').forEach(c => c.setAttribute('stroke-dasharray', c.dataset.dash));
        });
    }

    // ═══════ INTERACTIVIDAD DEL DASHBOARD ═══════

    // Selector de período
    document.querySelector('.period')?.addEventListener('click', e => {
        if (e.target.tagName !== 'BUTTON') return;
        document.querySelectorAll('.period button').forEach(b => b.classList.toggle('on', b === e.target));
    });

    // Tareas (toggle done)
    document.getElementById('tasksList')?.addEventListener('click', e => {
        const li = e.target.closest('li');
        if (li) li.classList.toggle('done');
    });

    // Campana (animación ring)
    document.getElementById('bellBtn')?.addEventListener('click', function () {
        this.classList.remove('ring');
        void this.offsetWidth; // Forzar reflow
        this.classList.add('ring');
    });

    // ═══════ INICIALIZACIÓN ═══════
    buildBars();
    buildDonut();

    requestAnimationFrame(() => {
        document.querySelector('.content-inner')?.classList.add('go');
    });
});