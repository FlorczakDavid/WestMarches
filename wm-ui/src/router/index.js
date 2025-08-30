import { createRouter, createWebHistory } from "vue-router";

function isTokenValid() {
  const exp = localStorage.getItem("exp");
  return !!exp && Number(exp) * 1000 > Date.now();
}

function getRoles() {
  const raw = localStorage.getItem("roles");
  if (!raw) return [];
  try {
    const parsed = JSON.parse(raw);
    if (Array.isArray(parsed)) return parsed;
  } catch (_) {}
  
  return String(raw)
    .split(/[, ]+/)
    .map((r) => r.trim())
    .filter(Boolean);
}

function hasRole(role) {
  return getRoles().includes(role);
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      redirect: () => (isTokenValid() ? { name: "map" } : { name: "login" }),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginView.vue"),
    },
    {
      path: "/register",
      name: "register",
      component: () => import("../views/RegisterView.vue"),
    },
    {
      path: "/map",
      name: "map",
      component: () => import("../views/MapView.vue"),
      meta: {
        requiresAuth: true,
        roles: ["ROLE_pc "], 
      },
    },
    {
      path: "/general-conditions-of-use-and-privay-policy",
      name: "legal",
      component: () => import("../views/LegalView.vue"),
    },
    {
      path: "/:pathMatch(.*)*",
      name: "NotFound",
      component: () => import("../views/NotFound.vue"),
    },
  ],
});

router.beforeEach((to) => {
  const authed = isTokenValid();

  if (to.meta?.requiresAuth && !authed) {
    return { name: "login", replace: true };
  }

  if (authed && (to.name === "login" || to.name === "register")) {
    return { name: "map", replace: true };
  }

  return true;
});

export default router;
