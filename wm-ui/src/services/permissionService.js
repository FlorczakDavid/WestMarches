class PermissionService {
  setUser(user) {
    localStorage.setItem("token", user.token);
    localStorage.setItem("roles", user.roles);
    console.log(localStorage)
  }
}

export default new PermissionService();