class PermissionService {
  setUser(user) {
    localStorage.setItem("token", user.token);
    localStorage.setItem("roles", user.roles);
    localStorage.setItem("exp", user.exp);
    console.log(localStorage);
  }
}

export default new PermissionService();
