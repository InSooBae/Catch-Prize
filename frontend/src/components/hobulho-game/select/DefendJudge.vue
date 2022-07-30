<template lang="">
  <div class="defend-container">
    <div class="tf-btn">
      <button 
        class="btn true-btn" 
        @click="defendJudge(true)">True</button>
      <button 
        class="btn false-btn"
        @click="defendJudge(false)">False</button>
    </div>
  </div>
</template>
<script>
const defendjudge = {
  defenderId : "1234",
  attackerId : "123",
  judge : {declaration : false}
}
export default{

  methods:{
    // 답에 따라서 TF 리턴하는 함수 
    defendJudge(answer){
      console.log(defendjudge.judge.declaration);
      console.log(answer);
      console.log(defendjudge.judge.declaration === answer? true : false)
      return (defendjudge.judge.declaration === answer? true : false)
    }
  }
}


</script>
<style>
.tf-btn {
  /* layout */
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  /* box */
  min-height: 100vh;
  max-width: 100vw;
  /* backgournd */
  /* font */
  /* etc */
}
.true-btn {
  /* layout */
  /* box */
  /* backgournd */
  /* font */
  /* etc */
  background-color: rgba(23,29,43,0.8);
  border: 7px;
  border-style: solid;
  border-color: #2572CC;
  color:white;
}
.true-btn:hover{
  background-color: #2572CC; 
  color: white;
  font-size: 1rem;
  /* border-radius: 30%; */
}
.false-btn {
  /* layout */
  /* box */
  border: 7px;
  border-style: solid;
  border-color: #F24822;
  /* backgournd */
  background-color: rgba(23,29,43,0.8);
  /* font */
  color:white;
  /* etc */  
}
.false-btn:hover{
  color: white;
  background-color: #F24822;
  font-size: 1rem;
}
.btn{
  /* layout */
  display: inline-block;
  /* box */
  border-radius: 2em;
  height : 10em;
  width: 10em;
  margin: 30px;
  /* background */
  /* background-color: rgba(23,29,43,0.8); */
  /* font */
  font-family: "PressStart2P";
  /* etc */
  transition: all 0.3s;
  cursor: pointer;
}
/* layout */
/* box */
/* backgournd */
/* font */
/* etc */
</style>
