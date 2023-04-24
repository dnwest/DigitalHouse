const isEmpty = (object) => {
  // return Object.keys(object).length === 0;
  for (const key in object) {
    if (object[key] === "") {
      return true;
    }
  }
};

export default isEmpty;
