// https://stackoverflow.com/questions/28772212/stun-turn-server-connectivity-test

const TURN_SERVER_URI = "turn:15.164.220.76";

function checkTURNServer(turnConfig, timeout) {
  return new Promise(function (resolve, reject) {
    let promiseResolved = false;

    setTimeout(function () {
      if (promiseResolved) return;
      resolve(false);
      promiseResolved = true;
    }, timeout || 5000);

    let myPeerConnection = window.RTCPeerConnection || window.mozRTCPeerConnection || window.webkitRTCPeerConnection // compatibility for firefox and chrome
    let pc = new myPeerConnection({ iceServers: [turnConfig] });
    let noop = () => { };
    pc.createDataChannel(""); // create a bogus data channel
    pc.createOffer((sdp) => {
      if (sdp.sdp.indexOf("typ relay") > -1) { // sometimes sdp contains the ice candidates
        promiseResolved = true;
        resolve(true);
      }
      pc.setLocalDescription(sdp, noop, noop);
    }, noop); // create offer and set local description
    pc.onicecandidate = (ice) => { // listen for candidate events
      if (promiseResolved || !ice || !ice.candidate || !ice.candidate.candidate || !(ice.candidate.candidate.indexOf('typ relay') > -1)) return;
      promiseResolved = true;
      resolve(true);
    };
  });
}

checkTURNServer({
  urls: TURN_SERVER_URI,
  username: "berry",
  credential: "straw"
}).then(function (bool) {
  console.log("Is TURN server active?", bool ? "YES" : "NO");
});
