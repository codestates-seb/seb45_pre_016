import axios from "axios";
axios.defaults.withCredentials = true;


export const login = async (data) => {
    try {
      const res = await axios({
        method: "post",
        data,
        headers: { Authorization: null },
        url: `http://localhost:8080/users/login`,
      });
      return res;
    } catch (e) {
      console.log(e);
    }
  };

  export const logout = async (accessToken) => {
    try {
      const token = `Bearer ${accessToken}`.toString("base64");
      const res = await axios({
        method: "post",
        url: `http://localhost:8080/users/logout`,
        headers: { Authorization: `${token}` },
      });
      return res;
    } catch (e) {
      console.log(e);
    }
  };
  

export const signUp = async (data) => {
    try {
      const res = await axios({
        method: "post",
        data,
        url: `http://localhost:8080/users/join`,
      });
      return res;
    } catch (e) {
      console.log(e);
    }
  };
  