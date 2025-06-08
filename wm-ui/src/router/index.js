import { createRouter, createWebHistory } from 'vue-router'

function isTokenValid() {
  const exp = localStorage.getItem('exp');
  return exp && parseInt(exp) * 1000 > Date.now();
}

function hasRole(role) {
  const roles = localStorage.getItem('roles');
  return roles && roles.includes(role);
}

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: () => {
        if (isTokenValid()) {
          return { name: 'map' };
        }
        return { name: 'login' };
      }
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterView.vue'),
    },
    {
      path: '/map',
      name: 'map',
      component: () => import('../views/MapView.vue'),
      beforeEnter: () => {
        return isTokenValid() && hasRole('ROLE_pc ') ? true : { name: 'login' };
      }
    },
    { 
      path: '/:pathMatch(.*)*',
      name: 'NotFound', 
      component: () => import('../views/NotFound.vue'), 
    },
  ],
})

export default router