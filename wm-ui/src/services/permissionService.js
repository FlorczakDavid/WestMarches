import auth from "./authService";

class PermissionService {
  setUser(user, email) {
    auth.login({
      email,
      token: user.token,
      roles: user.roles,
      exp: user.exp,
    });
  }

  clear() {
    auth.logout();
  }

  hasRole(role) {
    const r = auth.roles.value;
    if (!r) return false;
    try {
      if (r.trim().startsWith("[")) return JSON.parse(r).includes(role);
    } catch {}
    return String(r).split(",").map(s => s.trim()).includes(role);
  }
}

export default new PermissionService();
