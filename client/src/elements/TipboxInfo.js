/* eslint-disable no-unused-vars */
const tipboxInfo = [
  {
    id: "title",
    title: "Writing a good title",
    imgsrc: "",
    content: [
      "Your title should summarize the problem.",
      "You might find that you have a better idea of your title after writing out the rest of the question.",
    ],

    href: "",
  },
  {
    id: "problem",
    title: "Introduce the problem",
    imgsrc: "",
    content: [
      "Explain how you encountered the problem you’re trying to solve, and any difficulties that have prevented you from solving it yourself.",
    ],

    href: "",
  },
  {
    id: "tne",
    title: "Expand on the problem",
    imgsrc: "",
    content: [
      "Show what you’ve tried, tell us what happened, and why it didn’t meet your needs",
      "Not all questions benefit from including code, but if your problem is better understood with code you’ve written, you should include a minimal, reproducible example.",
    ],

    href: [
      { 1: "https://stackoverflow.com/help/minimal-reproducible-example" },
      {
        2: "https://meta.stackoverflow.com/questions/285551/why-should-i-not-upload-images-of-code-data-errors",
      },
      { 3: "https://stackoverflow.com/help/formatting" },
    ],
  },
  {
    id: "tag",
    title: "Adding tags",
    imgsrc: "",
    content: [
      "Tags help ensure that your question will get attention from the right people.",
      " Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.",
      "Learn more about tagging",
    ],

    href: [{ 1: "https://stackoverflow.com/help/tagging" }],
  },
  {
    id: "review",
    title: "Make sure we don’t already have an answer for your question",
    imgsrc: "",
    content: [
      "Stack Overflow is a huge database of knowledge.",
      "Please make sure your question isn’t already answered before posting, or your question might be closed as a duplicate.",
    ],

    href: [],
  },
];

export default tipboxInfo;