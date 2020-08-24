let login = new Vue({
	el: "#login_info",
	data: {
		onlogin: false,
		nickName: "匿名",
		loginId: "",
		memberInfo: {}
	},
	mounted: function() {
		axios.get("member", {params:{op:"info"}}).then(rt => {
			if (rt.status == 200) { // 说明请求成功
				if (rt.data.code == 200) { // 说明登录
					this.memberInfo = rt.data.data;
					this.nickName = rt.data.data.nickName;
					this.loginId = rt.data.data.mno;
					this.onlogin = true;
				}
			} else {
				this.onlogin = false;
			}
		})
	}
})