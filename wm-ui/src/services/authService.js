import { ref, computed } from "vue";

const email = ref(localStorage.getItem("user") || null);
const token = ref(localStorage.getItem("token") || null);
const roles = ref(localStorage.getItem("roles") || null);
const exp = ref(localStorage.getItem("exp") || null);

const isAuthenticated = computed(() => !!token.value);

function setItem(key, value) {
  if (value === null || value === undefined) localStorage.removeItem(key);
  else localStorage.setItem(key, value);
}

function login({ email: e, token: t, roles: r, exp: x } = {}) {
  if (e !== undefined) { email.value = e; setItem("user", e); }
  if (t !== undefined) { token.value = t; setItem("token", t); }
  if (r !== undefined) {
    const str = Array.isArray(r) ? r.join(",") : (typeof r === "string" ? r : JSON.stringify(r));
    roles.value = str; setItem("roles", str);
  }
  if (x !== undefined) { exp.value = x; setItem("exp", x); }
}

function logout() {
  email.value = null; token.value = null; roles.value = null; exp.value = null;
  localStorage.removeItem("user");
  localStorage.removeItem("token");
  localStorage.removeItem("roles");
  localStorage.removeItem("exp");
}

export default { email, token, roles, exp, isAuthenticated, login, logout };
