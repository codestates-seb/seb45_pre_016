import axios from "axios";

// axios.defaults.withCredentials = true; // 기본 설정에서 제거


export const signUp = async (data) => {
  try {
    const res = await axios({
      method: "post",
      data,
      url: `http://43.201.157.40:8080/users/signup`,
    });
    return res;
  } catch (e) {
    console.log(e);
  }
};

export const GetQuestions = async () => {
  try {
    const response = await axios.get(
      `http://43.201.157.40:8080/questions`,
      
    );
    const { data } = response;
    console.log(data);
    return response;
  } catch (e) {
    console.log(e);
  }
};
export const PostAsk = async () => {
  try {
    const response = await axios.post(
      "http://43.201.157.40:8080/questions/ask",
      {
        // 서버측에서 유저아이디 받기
        userId: localStorage.getItem("userId"),
        content: localStorage.getItem("content"),
        title: localStorage.getItem("title"),
        tagNames: ["java", "python"],
      },
      {
        headers: {
          Authorization: localStorage.getItem("Token"),
          dataType: "json",
        },
      }
    );
    console.log(response.data)

    return response;
  } catch (e) {
    console.log(e);
  }
};
export const PostAnswer = async () => {
  try {
    const response = await axios.post(
      `http://43.201.157.40:8080/questions/1/answers`,
      {
        // 서버측에서 유저아이디 받기
        userId: localStorage.getItem("userId"),
        content: localStorage.getItem("answer"),
      },
      {
        headers: {
          Authorization: localStorage.getItem("Token"),
          dataType: "json",
        },
      }
    );
    return response;
  } catch (e) {
    console.log(e);
  }
};

export const PatchAsk = async () => {
  try {
    const response = await axios.patch(
      "http://43.201.157.40:8080/questions/ask",
      {
        // 서버측에서 유저아이디 받기
        userId: localStorage.getItem("userId"),
        content: localStorage.getItem("content"),
        title: localStorage.getItem("title"),
        tagNames: ["java", "python"],
      },
      {
        headers: {
          Authorization: localStorage.getItem("Token"),
          dataType: "json",
        },
      }
    );
    return response;
  } catch (e) {
    console.log(e);
  }
};

export const DeleteAsk = async () => {
  try {
    const response = await axios.delete(
      "http://43.201.157.40:8080/questions/ask",
      {
        data: {
          // 서버측에서 유저아이디 받기
          userId: localStorage.getItem("userId"),
          content: localStorage.getItem("content"),
          title: localStorage.getItem("title"),
          tagNames: ["java", "python"],
        },
      },
      {
        headers: {
          Authorization: localStorage.getItem("Token"),
          dataType: "json",
        },
      }
    );
    return response;
  } catch (e) {
    console.log(e);
  }
};
