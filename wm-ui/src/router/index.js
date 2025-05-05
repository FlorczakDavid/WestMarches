import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: LoginView,
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
      // beforeEnter: () => {
      //   return localStorage.getItem('roles').includes('ROLE_pc ')
      // }
    },
    { 
      path: '/:pathMatch(.*)*',
      name: 'NotFound', 
      component: () => import('../views/NotFound.vue'), 
    },
  ],
})

router.beforeEach((to, from) => {
  console.log(to, from)
  if(to.name == 'map') { localStorage.getItem('roles').includes('ROLE_pc ') }
  return true;
})

// router.beforeResolve maybe for confirmation before cancelling
// router.beforeResolve(async from => {
//   if (from.meta.editpage) {
//     try {
//       await askForConfirmation()
//     } catch (error) {
//       if (error instanceof NotConfirmedError) {
//         // ... handle the error and then cancel the navigation
//         return false
//       } else {
//         // unexpected error, cancel the navigation and pass the error to the global handler
//         throw error
//       }
//     }
//   }
// })

export default router
