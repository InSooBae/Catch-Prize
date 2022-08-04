<template>
  <div class="declare-container">
    <div class="declare-card">
      <button>
        <img src="../assets/cake.svg" alt="오이케이크" />
      </button>
      <button>
        <img src="../assets/chicken.svg" alt="신호등치킨" />
      </button>
      <button>
        <img src="../assets/durian.svg" alt="두리안" />
      </button>
      <button>
        <img src="../assets/eggplant.svg" alt="가지" />
      </button>
      <button>
        <img src="../assets/insect.svg" alt="곤충튀김" />
      </button>
      <button>
        <img src="../assets/mint.svg" alt="민트초코" />
      </button>
    </div>
  </div>
</template>
<script>
export default {
  
}
</script>
<style>
.declare-container{
  display: flex;
  justify-content: center;
  align-items: center;
}
.declare-card{
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  height: 20rem;
  width: 24rem;
  min-width: 20rem;
  flex-flow: row wrap;
}
.declare-card button{
  height: 11rem;
  width:33%;
  padding: 0px ;
  margin: 0;
  background-color:transparent;
  border:none;
  
}
.declare-card button:hover{
  border: 12px;
  opacity: 20%;
  cursor: pointer;
}
</style>