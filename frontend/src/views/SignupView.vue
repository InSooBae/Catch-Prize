<template>
  <div>
    
    <label class="input-file-button" for="img-upload">
      File Upload
    </label>
    <ul class="signup_input">
      <input 
        type="file"
        id="img-upload"
        accept=".jpg, .jpeg, .png"
        style=display:none
      />
      <p>* nickname</p>
        <input 
          id="nickname"
          :v-model="nickname" 
          maxlength="10"
          placeholder="Enter your nickname"
          pattern="[A-Za-z]+"
          required
        />
      <p>* email</p>
      <!-- email 쉽게 입력할 수 있는 필드로 설정 -->
        <input 
          type="email"
          id="email"
          :v-model="email"
          placeholder="Enter your email"
          required
        />
      <p>birthday</p>
        <input 
          id="birthday"
          type="date"
          v-model="birthday"  
        />
      <div>
        <!-- 제출 버튼 -->
        <button>Sign Up!</button>
      </div>
    </ul>
  </div>
  
</template>

<style>
/* signup.css파일  */
@import '../assets/signup.css';
/* galmuri 한글폰트 cdn */
@import url('https://cdn.jsdelivr.net/npm/galmuri@latest/dist/galmuri.css');
/* press start 2p font cdn */
@import url('http://fonts.cdnfonts.com/css/press-start-2p');
</style>
