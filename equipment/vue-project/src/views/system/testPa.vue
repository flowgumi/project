<template>
  <nav class="fixed navbar w-full z-10">
    <div class="py-6 px-4 sm:px-6 lg:px-8">
      <div class="flex items-center justify-between">
        <div class="flex-shrink-0">
          <a href="index.html">
            <img src="images/Amanda.png" alt="Your Company">
          </a>
        </div>
        <div class="hidden md:block">
          <div class="flex items-baseline">
            <a href="index.html" class="font-heading uppercase text-base text-black hover:text-gray-500 py-2" aria-current="page">Home</a>
            <div class="group relative cursor-pointer">
              <a class="menu-hover font-heading uppercase text-base text-black hover:text-gray-500 px-10 py-2">
                Pages <iconify-icon icon="iconamoon:arrow-down-2-fill"></iconify-icon>
              </a>
              <div class="invisible absolute z-50 flex w-full flex-col bg-gray-100 py-1 ps-1 text-gray-800 shadow-xl group-hover:visible">
                <a href="about.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">About Us</a>
                <a href="blog.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Blog</a>
                <a href="blog-single.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Single Post</a>
                <a href="contact.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Contact</a>
                <a href="faq.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">FAQs</a>
                <a href="gallary.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Gallery</a>
              </div>
            </div>
            <a href="about.html" class="font-heading uppercase text-base text-black hover:text-gray-500 py-2 pe-5">About</a>
            <a href="blog.html" class="font-heading uppercase text-base text-black hover:text-gray-500 py-2 px-5">Blog</a>
            <a href="contact.html" class="font-heading uppercase text-base text-black hover:text-gray-500 py-2 ps-5">Contact</a>
            <a href="#" class="font-heading font-bold uppercase text-base text-black hover:text-gray-500 py-2 ps-5" target="_blank">Button</a>
          </div>
        </div>
        <div class="-mr-2 flex md:hidden mobile-menu-button">
          <button type="button" class="inline-flex items-center justify-center rounded-md p-2 text-black hover:bg-slate-700 hover:text-white">
            <span class="sr-only">Open main menu</span>
            <svg class="open-icon block h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
              <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
            </svg>
            <svg class="close-icon hidden h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
      </div>

      <div class="-mr-2 flex md:hidden mobile-menu-button">
      <button type="button" @click="toggleMobileMenu"
              class="inline-flex items-center justify-center rounded-md p-2 text-black hover:bg-slate-700 hover:text-white">
        <span class="sr-only">Open main menu</span>
        <svg v-if="!isMobileMenuOpen" class="block h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
             stroke="currentColor" aria-hidden="true">
          <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
        </svg>
        <svg v-if="isMobileMenuOpen" class="block h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
             stroke="currentColor" aria-hidden="true">
          <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
        </svg>
      </button>
    </div>
    <div v-show="isMobileMenuOpen" class="md:hidden bg-white">
      <div class="space-y-1 px-2 pb-3 pt-2 h-screen sm:px-3">
        <a href="index.html" class="font-heading block uppercase text-base text-black hover:text-gray-500 p-2">Home</a>
        <div class="group relative cursor-pointer">
          <div class="flex items-center justify-between">
            <a @click="togglePagesMenu" class="menu-hover font-heading block uppercase text-base text-black hover:text-gray-500 p-2">
              Pages <iconify-icon icon="iconamoon:arrow-down-2-fill"></iconify-icon>
            </a>
          </div>
          <div v-show="isPagesMenuOpen" class="absolute z-50 flex w-full flex-col bg-gray-100 py-1 px-2 text-gray-800 shadow-xl">
            <a href="about.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">About Us</a>
            <a href="blog.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Blog</a>
            <a href="blog-single.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Single Post</a>
            <a href="contact.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Contact</a>
            <a href="faq.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">FAQs</a>
            <a href="gallary.html" class="my-2 font-heading uppercase text-sm text-black hover:text-gray-500 md:mx-2">Gallery</a>
          </div>
        </div>
        <a href="about.html" class="font-heading block uppercase text-base text-black hover:text-gray-500 p-2">About</a>
        <a href="blog.html" class="font-heading block uppercase text-base text-black hover:text-gray-500 p-2">Blog</a>
        <a href="contact.html" class="font-heading block uppercase text-base text-black hover:text-gray-500 p-2">Contact</a>
        <a href="#" class="font-heading font-bold block uppercase text-base text-black hover:text-gray-500 p-2" target="_blank">Button</a>
      </div>
    </div>

    </div>
  </nav>
  
</template>

<script>
export default {
  name: "MyComponent",
  // 你可以在这里添加JavaScript逻辑
};
</script>

<style scoped>
/* 将CSS样式直接写在这里，或通过@import引入外部CSS */
@import 'https://cdn.bootcdn.net/ajax/libs/Swiper/9.4.1/swiper-bundle.min.css';
@import 'https://fonts.googleapis.com/css2?family=Jaldi&family=Merriweather:wght@300;400;700&display=swap';
@import '../../assets/amanda/assets/index-ix7uzjuy.css';
</style>
