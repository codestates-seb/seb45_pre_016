/* eslint-disable no-unused-vars */
const InputInfo = [
  {
    id: "title",
    title: "Title",
    isHidden: false,
    description:
      "Be specific and imagine you’re asking a question to another person.",
    placeholder:
      "e.g. Is there an R function for finding the index of an element in a vector?",
  },
  {
    id: "problem",
    title: "What are the details of your problem?",
    isHidden: true,
    description:
      "Introduce the problem and expand on what you put in the title. Minimum 20 characters.",
      placeholder:
        "",
  },
  {
    id: "tne",
    title: "What did you try and what were you expecting?",
    isHidden: true,
    description:
      "Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.",
      placeholder:
        "",
  },
  {
    id: "tag",
    title: "Tags",
    isHidden: true,
    description:
      "Add up to 5 tags to describe what your question is about. Start typing to see suggestions.",
      placeholder:
        "",
  },
  {
    id: "review",
    title:
      "Review questions already on Stack Overflow to see if your question is a duplicate.",
    isHidden: true,
    description:
      "Confirm that none of these existing posts on Stack Overflow answers your question.",
      placeholder:
        "",
  },
];

export default InputInfo;
