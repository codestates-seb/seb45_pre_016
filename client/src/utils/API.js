import axios from "axios";

export const GetQuestions = async () => {
  const response = await axios.get("http://43.201.157.40:8080/questions");
  const { data } = response;
  console.log(data);
};
export const PostAsk = async () => {
    const response = await axios.post("http://43.201.157.40:8080/questions/ask", {
        "userId" : "kim",
        "content" : localStorage.getItem('body'),
        "title" : localStorage.getItem('title')
    });
    const { data } = response;
    console.log(data);
  };
