(function () {
    const ENTER_SCALE = 1.02;
    const LEAVE_SCALE = 1.0;

    const BASE_SHADOW = '0 2px 10px rgba(0,0,0,0.08)';
    const HOVER_SHADOW = '0 6px 14px rgba(0,0,0,0.12), 0 12px 24px rgba(0,0,0,0.10)';

    function applyHover(card) {
        card.style.setProperty('--tilt-shadow', HOVER_SHADOW);
        card.style.setProperty('--scale', ENTER_SCALE.toString());
    }

    function clearHover(card) {
        card.style.setProperty('--tilt-shadow', BASE_SHADOW);
        card.style.setProperty('--scale', LEAVE_SCALE.toString());
    }

    function setupCard(card) {
        clearHover(card);
        card.addEventListener('mouseenter', () => applyHover(card));
        card.addEventListener('mouseleave', () => clearHover(card));
        card.addEventListener('focus',     () => applyHover(card));
        card.addEventListener('blur',      () => clearHover(card));
    }

    document.addEventListener('DOMContentLoaded', () => {
        document.querySelectorAll('.anime-card').forEach(setupCard);
    });
})();