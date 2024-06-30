import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [

  {
    path: '/testUR',
    name: 'indexUR',
    component: () => import('../views/system/IndexPageUserReg.vue')
  },
  {
    path: '/testFP',
    name: 'indexFP',
    component: () => import('../views/system/IndexPageFirstPage.vue')
  },
  {
    path: '/statistics',   //statistics
    name: 'StatisticsPage',
    component: () => import('../views/system/StatisticPage.vue')
  },
  {
    path: '/t',   //statistics
    name: 't',
    component: () => import('../views/system/testPa.vue')
  },



  {
    path: '/',
    redirect: '/testFP'
  },


  {
    path: '/firstpage',
    name: 'firstpage',
    component: () => import( '../views/system/FirstPage.vue')
  },
  // {
  //   path: '/',
  //   redirect: '/firstpage'
  // },
  {
    path: '/about',
    name: 'about',
    component: () => import( '../views/AboutView.vue')
  },
  {
    path:'/register',
    name: 'UserRegister',
    component:() => import( '../views/system/UserRegister.vue')
  },
  {
    path:'/home',
    name: 'HomePage',
    component:() => import( '../views/system/HomePage.vue')
  },
  {
    path:'/borrow',
    name: 'borrowPage',
    component:() => import( '../views/system/borrowPage.vue')
  },
  {
    path:'/query',
    name: 'queryPage',
    component:() => import( '../views/system/queryPage.vue')
  },
  {
    path:'/scrap',
    name: 'scrapPage',
    component:() => import( '../views/system/scrapPage.vue')
  },
  {
    path:'/return',
    name: 'ReturnPage',
    component:() => import( '../views/system/ReturnPage.vue')
  },
  
]

const router = new VueRouter({
  routes
})

export default router
