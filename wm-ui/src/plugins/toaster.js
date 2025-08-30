import { Toast } from "bootstrap";

function toast(cls, message) {
    const container = document.querySelector(`#toaster>.toast`);
    const classes = container.classList;
    classes.add(cls);
    const body = container.querySelector('.toast-body');
    body.textContent = message;
    container.addEventListener('hidden.bs.toast', () => {
        classes.remove(cls);
        body.textContent = '';
    }, { once: true });
    const toaster = Toast.getOrCreateInstance(container);
    toaster.show();
}

export default {
    install: (app) => {
        app.config.globalProperties.$toaster = {
            success: (msg) => { toast('text-bg-success', msg); },
            error: (msg) => { toast('text-bg-danger', msg); },
            info: (msg) => { toast('text-bg-info', msg); },
            warning: (msg) => { toast('text-bg-warning', msg); }
        }
    }
};
